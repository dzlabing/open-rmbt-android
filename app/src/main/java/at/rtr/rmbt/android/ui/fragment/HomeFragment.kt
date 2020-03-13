package at.rtr.rmbt.android.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import at.rmbt.client.control.IpProtocol
import at.rtr.rmbt.android.R
import at.rtr.rmbt.android.databinding.FragmentHomeBinding
import at.rtr.rmbt.android.di.viewModelLazy
import at.rtr.rmbt.android.ui.activity.LoopConfigurationActivity
import at.rtr.rmbt.android.ui.activity.LoopInstructionsActivity
import at.rtr.rmbt.android.ui.activity.MeasurementActivity
import at.rtr.rmbt.android.ui.activity.PreferenceActivity
import at.rtr.rmbt.android.ui.dialog.IpInfoDialog
import at.rtr.rmbt.android.ui.dialog.LocationInfoDialog
import at.rtr.rmbt.android.ui.dialog.MessageDialog
import at.rtr.rmbt.android.ui.dialog.OpenGpsSettingDialog
import at.rtr.rmbt.android.ui.dialog.OpenLocationPermissionDialog
import at.rtr.rmbt.android.util.InfoWindowStatus
import at.rtr.rmbt.android.util.ToolbarTheme
import at.rtr.rmbt.android.util.changeStatusBarColor
import at.rtr.rmbt.android.util.listen
import at.rtr.rmbt.android.viewmodel.HomeViewModel
import at.specure.location.LocationProviderState.DISABLED_APP
import at.specure.location.LocationProviderState.DISABLED_DEVICE
import at.specure.location.LocationProviderState.ENABLED
import at.specure.measurement.MeasurementService
import at.specure.util.toast

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModelLazy()
    private val binding: FragmentHomeBinding by bindingLazy()

    override val layoutResId = R.layout.fragment_home

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.state = homeViewModel.state
        updateTransparentStatusBarHeight(binding.statusBarStub)
        homeViewModel.isConnected.listen(this) {
            activity?.window?.changeStatusBarColor(if (it) ToolbarTheme.BLUE else ToolbarTheme.GRAY)
        }

        homeViewModel.signalStrengthLiveData.listen(this) {
            homeViewModel.state.signalStrength.set(it)
        }

        homeViewModel.activeNetworkLiveData.listen(this) {
            homeViewModel.state.activeNetworkInfo.set(it)
        }

        homeViewModel.locationStateLiveData.listen(this) {
            homeViewModel.state.isLocationEnabled.set(it)
        }

        homeViewModel.ipV4ChangeLiveData.listen(this) {
            homeViewModel.state.ipV4Info.set(it)
        }

        homeViewModel.ipV6ChangeLiveData.listen(this) {
            homeViewModel.state.ipV6Info.set(it)
        }

        binding.btnSetting.setOnClickListener {
            startActivity(Intent(requireContext(), PreferenceActivity::class.java))
        }
        binding.tvInfo.setOnClickListener {
            homeViewModel.state.infoWindowStatus.set(InfoWindowStatus.GONE)
        }

        binding.btnIpv4.setOnClickListener {
            val ipV4InfoDialog = IpInfoDialog.instance(IpProtocol.V4)
            ipV4InfoDialog?.show(activity)
        }

        binding.btnIpv6.setOnClickListener {
            val ipV6InfoDialog = IpInfoDialog.instance(IpProtocol.V6)
            ipV6InfoDialog?.show(activity)
        }

        binding.btnLocation.setOnClickListener {

            context?.let {
                homeViewModel.state.isLocationEnabled.get()?.let {
                    when (it) {
                        ENABLED -> LocationInfoDialog.instance().show(activity)
                        DISABLED_APP -> OpenLocationPermissionDialog.instance().show(activity)
                        DISABLED_DEVICE -> OpenGpsSettingDialog.instance().show(activity)
                    }
                }
            }
        }

        binding.ivSignalLevel.setOnClickListener {
            if (homeViewModel.isConnected.value == true) {
                if (!homeViewModel.clientUUID.value.isNullOrEmpty()) {
                    if (homeViewModel.state.isLoopModeActive.get()) {
                        LoopConfigurationActivity.start(requireContext())
                    } else {
                        MeasurementService.startTests(requireContext())
                        MeasurementActivity.start(requireContext())
                    }
                } else {
                    MessageDialog.instance(R.string.client_not_registered).show(activity)
                }
            } else {
                MessageDialog.instance(R.string.home_no_internet_connection).show(activity)
            }
        }

        binding.btnUpload.setOnClickListener {
            homeViewModel.activeSignalMeasurementLiveData.value?.let { active ->
                if (!active) {
                    requireContext().toast(R.string.toast_signal_measurement_enabled)
                }
            }
            homeViewModel.toggleService()
        }

        homeViewModel.activeSignalMeasurementLiveData.listen(this) {
            homeViewModel.state.isSignalMeasurementActive.set(it)
        }

        binding.btnLoop.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (!binding.btnLoop.isChecked) {
                    LoopInstructionsActivity.start(this, CODE_LOOP_INSTRUCTIONS)
                } else {
                    homeViewModel.state.isLoopModeActive.set(false)
                }
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_LOOP_INSTRUCTIONS) {
            if (resultCode == Activity.RESULT_OK) {
                homeViewModel.state.isLoopModeActive.set(true)
            } else {
                homeViewModel.state.isLoopModeActive.set(false)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        homeViewModel.permissionsWatcher.notifyPermissionsUpdated()
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.attach(requireContext())
        if (homeViewModel.permissionsWatcher.requiredPermissions.isNotEmpty()) {
            requestPermissions(
                homeViewModel.permissionsWatcher.requiredPermissions,
                PERMISSIONS_REQUEST_CODE
            )
        }
        startTimerForInfoWindow()
        homeViewModel.state.checkConfig()
    }

    override fun onStop() {
        super.onStop()
        homeViewModel.detach(requireContext())
    }

    /**
     * If user not doing any action within 2 second, information window display
     */
    private fun startTimerForInfoWindow() {
        Handler().postDelayed({

            if (homeViewModel.state.infoWindowStatus.get() == InfoWindowStatus.NONE) {
                homeViewModel.state.infoWindowStatus.set(InfoWindowStatus.VISIBLE)
            }
        }, INFO_WINDOW_TIME_MS)
    }

    companion object {
        private const val PERMISSIONS_REQUEST_CODE: Int = 10
        private const val INFO_WINDOW_TIME_MS: Long = 2000
        private const val CODE_LOOP_INSTRUCTIONS = 13
    }
}