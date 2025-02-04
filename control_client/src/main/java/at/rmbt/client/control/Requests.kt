/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.rmbt.client.control

import androidx.annotation.Keep
import com.google.gson.JsonArray
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// TODO Remove mocked values
@Keep
data class NewsRequestBody(
    val language: String = "en",
    val lastNewsUid: Long? = null,
    @SerializedName("plattform")
    val platform: String = "Android",
    val softwareVersionCode: String = "0",
    val uuid: String?
)

@Keep
data class SettingsRequestBody(
    val type: String,
    val name: String,
    val language: String,
    @SerializedName("plattform")
    val platform: String,
    @SerializedName("os_version")
    val osVersion: String,
    @SerializedName("api_level")
    val apiLevel: String,
    val device: String,
    val model: String,
    val product: String,
    val timezone: String,
    val softwareRevision: String,
    val softwareVersionCode: String,
    val softwareVersionName: String,
    @SerializedName("version_name")
    val versionName: String,
    @SerializedName("version_code")
    val versionCode: String,
    val uuid: String,
    @SerializedName("user_server_selection")
    val userServerSelectionEnabled: Boolean,
    @SerializedName("terms_and_conditions_accepted_version")
    var tacVersion: Int,
    @SerializedName("terms_and_conditions_accepted")
    var tacAccepted: Boolean,
    var capabilities: CapabilitiesBody,
    @SerializedName("uuid_legacy")
    var uuidLegacy: String?
)

@Keep
data class IpRequestBody(
    @SerializedName("plattform")
    val platform: String,
    @SerializedName("os_version")
    val osVersion: String,
    @SerializedName("api_level")
    val apiLevel: String,
    val device: String,
    val model: String,
    val product: String,
    val language: String,
    val timezone: String,
    val softwareRevision: String,
    val softwareVersionCode: String,
    val softwareVersionName: String,
    val type: String,
    val location: TestLocationBody?,
    @SerializedName("last_signal_item")
    val lastSignalItem: SignalItemBody?,
    val uuid: String?,
    val capabilities: CapabilitiesBody = CapabilitiesBody()
)

// TODO Remove mocked values
@Keep
data class TestRequestRequestBody(
    @SerializedName("plattform")
    val platform: String? = "Android",
    val softwareVersionCode: Int?,
    val ndt: Boolean? = false,
    val previousTestStatus: String? = "END",
    val testCounter: Int? = -1,
    val softwareRevision: String?,
    val softwareVersion: String?,
    @SerializedName("user_server_selection")
    val userServerSelection: Boolean?,
    @SerializedName("prefer_server")
    val preferServer: String?,
    @SerializedName("num_threads")
    val numberOfThreads: Int?,
    @SerializedName("protocol_version")
    val protocolVersion: String? = "ipv4",
    val location: TestLocationBody? = null,
    val time: Long? = 1571664344999,
    val timezone: String = "Europe/Bratislava",
    val client: String = "RMBT",
    val version: String = "0.3",
    val type: String = "MOBILE",
    val uuid: String = "c373f294-f332-4f1a-999e-a87a12523f4b",
    val language: String? = "en",
    val capabilities: CapabilitiesBody = CapabilitiesBody(),
    @SerializedName("loopmode_info")
    val loopModeInfo: LoopModeInfo
)

@Keep
data class ServerTestResultBody(

    /**
     * test uuid, generated by the server, send to client via /testRequest
     */
    @SerializedName("test_uuid")
    val testUUID: String,

    /**
     * client uuid, generated by control server sent to client via /settings
     */
    @SerializedName("uuid")
    val clientUUID: String,

    /**
     * 2 character language code
     */
    val language: String,

    /**
     * capabilities of the client
     */
    val capabilities: CapabilitiesBody = CapabilitiesBody()
)

@Keep
data class TestResultBody(

    /**
     * Platform constant ("Android" for android client)
     */
    @SerializedName("plattform")
    val platform: String,

    /**
     * Client uuid
     */
    @SerializedName("client_uuid")
    val clientUUID: String,

    /**
     * Type of the client {"RMBT", "RMBTws", "HW-PROBE"}, for android devices it is "RMBT"
     */
    @SerializedName("client_name")
    val clientName: String,

    /**
     * Version name of the client version
     */
    @SerializedName("client_version")
    val clientVersion: String,

    /**
     * Language code of the current locale (ISO 639)
     */
    @SerializedName("client_language")
    val clientLanguage: String,

    /**
     * Time of the test in millis
     */
    @SerializedName("time")
    val timeMillis: Long,

    /**
     * Test token generated by control server
     */
    @SerializedName("test_token")
    val token: String,

    /**
     * Port of the test server the test was performed on
     */
    @SerializedName("test_port_remote")
    val portRemote: Int,

    /**
     * Bytes downloaded during download phase
     */
    @SerializedName("test_bytes_download")
    val bytesDownloaded: Long,

    /**
     * Bytes uploaded during upload phase
     */
    @SerializedName("test_bytes_upload")
    val bytesUploaded: Long,

    /**
     * Bytes uploaded during the whole test
     */
    @SerializedName("test_total_bytes_download")
    val totalBytesDownloaded: Long,

    /**
     * Bytes uploaded during the whole test
     */
    @SerializedName("test_total_bytes_upload")
    val totalBytesUploaded: Long,

    /**
     * Test encryption type string (e.g. "TLSv1.2 (TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256)")
     */
    @SerializedName("test_encryption")
    val encryptionType: String?,

    /**
     * Client public ip address, sent by control server
     */
    @SerializedName("test_ip_local")
    val clientPublicIp: String?,

    /**
     * Server public ip address, sent by control server
     */
    @SerializedName("test_ip_server")
    val serverPublicIp: String?,

    /**
     * Duration of the download phase in nanoseconds
     */
    @SerializedName("test_nsec_download")
    val downloadDurationNanos: Long,

    /**
     * Duration of the upload phase in nanoseconds
     */
    @SerializedName("test_nsec_upload")
    val uploadDurationNanos: Long,

    /**
     * Number of threads used during the measurement
     */
    @SerializedName("test_num_threads")
    val threadCount: Int,

    /**
     * Download speed in kbs
     */
    @SerializedName("test_speed_download")
    val downloadSpeedKbs: Long,

    /**
     * Upload speed in kbs
     */
    @SerializedName("test_speed_upload")
    val uploadSpeedKbs: Long,

    /**
     * Shortest ping in nanos
     */
    @SerializedName("test_ping_shortest")
    val shortestPingNanos: Long,

    /**
     * Bytes downloaded on the interface during test
     */
    @SerializedName("test_if_bytes_download")
    val downloadedBytesOnInterface: Long,

    /**
     * Bytes uploaded on the interface during test
     */
    @SerializedName("test_if_bytes_upload")
    val uploadedBytesOnInterface: Long,

    /**
     * Bytes downloaded on the interface during download phase
     */
    @SerializedName("testdl_if_bytes_download")
    val downloadedBytesOnDownloadInterface: Long,

    /**
     * Bytes uploaded on the interface during download phase
     */
    @SerializedName("testdl_if_bytes_upload")
    val uploadedBytesOnDownloadInterface: Long,

    /**
     * Bytes downloaded on the interface during download phase
     */
    @SerializedName("testul_if_bytes_download")
    val downloadedBytesOnUploadInterface: Long,

    /**
     * Bytes uploaded on the interface during download phase
     */
    @SerializedName("testul_if_bytes_upload")
    val uploadedBytesOnUploadInterface: Long,

    /**
     * Relative start time of download phase in nanos
     */
    @SerializedName("time_dl_ns")
    val timeDownloadOffsetNanos: Long?,

    /**
     * Relative start time of download phase in nanos
     */
    @SerializedName("time_ul_ns")
    val timeUploadOffsetNanos: Long?,

    val product: String,

    @SerializedName("os_version")
    val osVersion: String,

    @SerializedName("api_level")
    val apiLevel: String,

    val device: String,
    val model: String,

    @SerializedName("client_software_version")
    val clientSoftwareVersion: String,

    /**
     * Server id for the network TODO:(shared/Helperfunctions line 156 - conversion from number to name)
     */
    @SerializedName("network_type")
    val networkType: String,
    val geoLocations: List<TestLocationBody>?,
    val capabilities: CapabilitiesBody,
    val pings: List<PingBody>?,
    val radioInfo: RadioInfoBody?,
    @SerializedName("speed_detail")
    val speedDetail: List<SpeedBody>?,
    val cellLocations: List<CellLocationBody>?,
    @SerializedName("android_permission_status")
    val permissionStatuses: List<PermissionStatusBody>?,

    @SerializedName("developer_mode")
    val developerModeEnabled: Boolean,

    @SerializedName("user_server_selection")
    val userServerSelectionEnabled: Boolean,

    @SerializedName("user_loop_mode")
    val loopModeEnabled: Boolean,

    /**
     * mcc-mnc of the operator network, mobile networks only, e.g. "231-06"
     */
    @SerializedName("telephony_network_operator")
    val telephonyNetworkOperator: String?,

    /**
     * true if the network is roaming, mobile networks only
     */
    @SerializedName("telephony_network_is_roaming")
    val telephonyNetworkIsRoaming: String?,

    /**
     * country code for network, mobile networks only e.g. "en"
     */
    @SerializedName("telephony_network_country")
    val telephonyNetworkCountry: String?,

    /**
     * name of the network operator, mobile networks only, e.g. "O2 - SK"
     */
    @SerializedName("telephony_network_operator_name")
    val telephonyNetworkOperatorName: String?,

    /**
     * name of the sim operator, mobile networks only, e.g. "O2 - SK"
     */
    @SerializedName("telephony_network_sim_operator_name")
    val telephonyNetworkSimOperatorName: String?,

    /**
     * mcc-mnc of the sim operator, mobile networks only e.g."231-06"
     */
    @SerializedName("telephony_network_sim_operator")
    val telephonyNetworkSimOperator: String?,

    /**
     * phone type, mobile networks only e.g. "1"
     */
    @SerializedName("telephony_phone_type")
    val telephonyPhoneType: String?,

    /**
     * data state, mobile networks only e.g. "2"
     */
    @SerializedName("telephony_data_state")
    val telephonyDataState: String?,

    /**
     * name of the access point, mobile networks only e.g. "o2internet"
     */
    @SerializedName("telephony_apn")
    val telephonyApn: String?,

    /**
     * country code of the sim card issuer, mobile networks only, e.g. "sk"
     */
    @SerializedName("telephony_network_sim_country")
    val telephonyNetworkSimCountry: String?,

    @SerializedName("telephony_sim_count")
    val telephonySimCount: String?,

    @SerializedName("dual_sim")
    val telephonyHasDualSim: Boolean?,

    /**
     * Contains info about how data sim was selected and how cell info was chosen for mobile data connection
     */
    @SerializedName("dual_sim_detection_method")
    val dualSimDetectionMethod: String?,

    /**
     * Contains information about the way the 5G connection is handled
     */
    @SerializedName("telephony_nr_connection")
    val telephonyNRConnection: String?,

    /**
     * Wifi supplicant state e.g. "COMPLETED"
     */
    @SerializedName("wifi_supplicant_state")
    var wifiSupplicantState: String?,

    /**
     * Wifi supplicant state detail e.g. "OBTAINING_IPADDR"
     */
    @SerializedName("wifi_supplicant_state_detail")
    var wifiSupplicantStateDetail: String?,

    /**
     * SSID of the wifi network
     */
    @SerializedName("wifi_ssid")
    var wifiSsid: String?,

    /**
     * Id of the wifi network
     */
    @SerializedName("wifi_network_id")
    var wifiNetworkId: String?,

    /**
     * BSSID of the wifi network
     */
    @SerializedName("wifi_bssid")
    var wifiBssid: String?,

    /**
     * Count of unsuccessful submissions
     */
    @SerializedName("test_submission_retry_count")
    var submissionRetryCount: Int,

    /**
     * Reason of the test finishing, provided as int value, example - "0" for Success
     */
    @SerializedName("test_status")
    var testStatus: String,

    /**
     * Phase was the last done by the client, provided like value from TestStatus except "ERROR" and "ABORT"
     */
    @SerializedName("last_client_status")
    var lastClientStatus: String?,

    /**
     * Phase was the last done by the QoS test, provided like value from TestStatus except "ERROR" and "ABORT"
     */
    @SerializedName("last_qos_status")
    var lastQoSStatus: String?,

    /**
     * Stacktrace of IllegalNetworkChangeException exception grabbed from RMBTClient which was happened during the test.
     * May be null if test was success or cancelled
     */
    @SerializedName("test_error_cause")
    var testErrorCause: String? = null, // todo add an catch IllegalNetworkChangeException

    /**
     * Test tag added to each test, can be set in the developer mode settings
     */
    @SerializedName("tag")
    var testTag: String? = null
)

@Keep
data class LoopModeInfo(
    val uid: Long?,
    @SerializedName("test_uuid")
    val testUUID: String?,
    @SerializedName("client_uuid")
    val clientUUID: String?,
    @SerializedName("max_delay")
    val maxDelaySec: Int?,
    @SerializedName("max_movement")
    val maxMovementMeters: Int?,
    @SerializedName("max_tests")
    val maxTests: Int?,
    @SerializedName("test_counter")
    val testCounter: Int?,
    @SerializedName("loop_uuid")
    val loopUUID: String?
)

@Keep
data class PingBody(
    /**
     * ping value in nanos on client side, used to get shortest ping
     */
    @SerializedName("value")
    val differenceClient: Long,
    /**
     * ping value in nanos on server side, used to get median ping
     */
    @SerializedName("value_server")
    val differenceServer: Long,
    /**
     * Relative time from the start of the test in nanos
     */
    @SerializedName("time_ns")
    val timeNanos: Long
)

@Keep
data class RadioInfoBody(
    /**
     * Cell infos tracked during measurement
     */
    val cells: List<CellInfoBody>?,
    /**
     * Signal values tracked during measurement
     */
    val signals: List<SignalBody>?
)

@Keep
data class CellInfoBody(

    /**
     * True if cell is active (connected one)
     */
    val active: Boolean,

    /**
     * Area code from mobile cells (Mobile cells only)
     */
    @SerializedName("area_code")
    val areaCode: Int?,

    /**
     * Generated uuid for the current cell
     */
    val uuid: String,

    /**
     * Channel number of the cell
     */
    @SerializedName("channel_number")
    val channelNumber: Int?,

    /**
     * Id of the location, mobile only
     */
    @SerializedName("location_id")
    val locationId: Int?,

    /**
     * Code of the country of the operator, mobile only
     */
    val mcc: Int?,

    /**
     * code of the operator, mobile only
     */
    val mnc: Int?,

    /**
     * scrambling code, mobile only
     */
    @SerializedName("primary_scrambling_code")
    val primaryScramblingCode: Int?,

    /**
     * 2G, 3G, 4G, 5G, WLAN
     */
    val technology: String?,

    /**
     * true if it is connected cell, same as active ???
     */
    val registered: Boolean
)

@Keep
data class SignalBody(

    /**
     * Generated uuid of the cell
     */
    @SerializedName("cell_uuid")
    val cellUuid: String,

    /**
     * Server id for network type
     */
    @SerializedName("network_type_id")
    val networkTypeId: Int?,

    /**
     * Only for non 4G signal types
     */
    val signal: Int?,

    /**
     * Error rate for non 4G and WIFI signal
     */
    @SerializedName("bit_error_rate")
    val bitErrorRate: Int?,

    /**
     * Declared wifi speed, only WIFI
     */
    @SerializedName("wifi_link_speed")
    val wifiLinkSpeed: Int?,

    /**
     * Only for 4G/LTE signal strength
     */
    @SerializedName("lte_rsrp")
    val lteRsrp: Int?,

    /**
     * Only for 4G/LTE signal quality
     */
    @SerializedName("lte_rsrq")
    val lteRsrq: Int?,

    /**
     * Only for 4G/LTE
     */
    @SerializedName("lte_cqi")
    val lteCqi: Int?,

    /**
     * Only for 4G/LTE
     */
    @SerializedName("lte_rssnr")
    val lteRssnr: Int?,

    /**
     * timing advance
     */
    @SerializedName("timing_advance")
    val timingAdvance: Int?,

    /**
     * Relative time in nanos from the start of the test
     */
    @SerializedName("time_ns")
    val timeNanos: Long,

    /**
     * relative timestamp from the start of the test, but time of the last update of the cells (the last updated cells do not have this field filled)
     */
    @SerializedName("time_ns_last")
    val timeLastNanos: Long?,

    /**
     * Only for 5G networks
     */
    @SerializedName("nr_csi_rsrp")
    val nrCsiRsrp: Int?,

    @SerializedName("nr_csi_rsrq")
    val nrCsiRsrq: Int?,

    @SerializedName("nr_csi_sinr")
    val nrCsiSinr: Int?,

    @SerializedName("nr_ss_rsrp")
    val nrSsRsrp: Int?,

    @SerializedName("nr_ss_rsrq")
    val nrSsRsrq: Int?,

    @SerializedName("nr_ss_sinr")
    val nrSsSinr: Int?,

    // debug only to know from which source is signal obtained
    @SerializedName("signal_source")
    val signalSource: String
)

@Keep
data class SpeedBody(

    /**
     * possible values ["upload", "download"]
     */
    val direction: String,

    /**
     * Thread number of the test which value came from
     */
    val thread: Int,

    /**
     * Time from the test start in nanos
     */
    @SerializedName("time")
    val timeNanos: Long,

    /**
     * Actually transferred bytes by the thread
     */
    val bytes: Long
)

@Keep
data class TestLocationBody(
    @SerializedName("geo_lat")
    val latitude: Double,
    @SerializedName("geo_long")
    val longitude: Double,
    val provider: String?,
    val speed: Float,
    val altitude: Double,

    /**
     * Timestamp of the information in millis from Location.time
     */
    @SerializedName("tstamp")
    val timeMillis: Long,

    /**
     * Relative time from the start of the test System.currentTimeMillis() - millis of the test start
     */
    @SerializedName("time_ns")
    val timeNanos: Long,
    val age: Long,
    val accuracy: Float,
    val bearing: Float,
    @SerializedName("mock_location")
    val mockLocation: Boolean,
    val satellites: Int?
)

@Keep
data class CellLocationBody(

    /**
     * timestamp of the information
     */
    @SerializedName("time")
    val timeMillis: Long,

    /**
     * timestamp of the information
     */
    @SerializedName("time_ns")
    val timeNanos: Long,

    /**
     * id of the location
     */
    @SerializedName("location_id")
    val locationId: Int?,

    @SerializedName("area_code")
    val areaCode: Int?,

    /**
     * scrambling code, -1 if not available
     */
    @SerializedName("primary_scrambling_code")
    val primaryScramblingCode: Int
)

@Keep
data class PermissionStatusBody(

    /**
     * Name of the permission
     */
    val permission: String,

    /**
     * true if the permission was granted
     */
    val status: Boolean
)

@Keep
data class SignalItemBody(
    val time: Long = 1570452441549,
    @SerializedName("network_type_id")
    val networkTypeId: Int,
    @SerializedName("gsm_bit_error_rate")
    val gsmBitErrorRate: Int? = null,
    @SerializedName("lte_rsrp")
    val lteRsrp: Int? = null,
    @SerializedName("lte_rsrq")
    val lteRsrq: Int? = null,
    @SerializedName("lte_rssnr")
    val lteRssnr: Int? = null,
    @SerializedName("lte_cqi")
    val lteCqi: Int? = null,
    @SerializedName("wifi_link_speed")
    val wifiLinkSpeed: Int? = null,
    @SerializedName("wifi_rssi")
    val wifiRssi: Int? = null
)

@Keep
data class CapabilitiesBody(
    val classification: ClassificationBody = ClassificationBody(4),
    val qos: QoSBody = QoSBody(false),
    @SerializedName("RMBThttp")
    val rmbtHttpStatus: Boolean = true
)

@Keep
data class ClassificationBody(val count: Int)

@Keep
data class QoSBody(
    @SerializedName("supports_info")
    val supportsInfo: Boolean
)

@Keep
data class HistoryRequestBody(
    @SerializedName("uuid")
    val clientUUID: String,
    @SerializedName("result_offset")
    val offset: Int,
    @SerializedName("result_limit")
    val limit: Int,
    val language: String,
    val devices: List<String>?,
    val networks: List<String>?,
    val capabilities: CapabilitiesBody
)

@Keep
data class QoSResultBody(
    @SerializedName("client_uuid")
    val clientUUID: String,
    @SerializedName("client_name")
    val clientName: String,
    @SerializedName("client_version")
    val clientVersion: String,
    @SerializedName("client_language")
    val clientLanguage: String,
    @SerializedName("time")
    val timeMillis: Long,
    @SerializedName("test_token")
    val testToken: String,
    @SerializedName("qos_result")
    val qosResult: JsonArray
)

@Keep
data class TestResultDetailBody(
    /**
     * test uuid, generated by the server, send to client via /testRequest
     */
    @SerializedName("test_uuid")
    val testUUID: String,

    /**
     * client uuid, generated by control server sent to client via /settings
     */
    @SerializedName("uuid")
    val clientUUID: String,

    /**
     * 2 character language code
     */
    val language: String
)

@Keep
data class QosTestResultDetailBody(
    /**
     * test uuid, generated by the server, send to client via /testRequest
     */
    @SerializedName("test_uuid")
    val testUUID: String,

    /**
     * client uuid, generated by control server sent to client via /settings
     */
    @SerializedName("uuid")
    val clientUUID: String,

    /**
     * 2 character language code
     */
    val language: String,
    val capabilities: CapabilitiesBody?
)

@Keep
data class GetSyncCodeBody(

    @SerializedName("uuid")
    val clientUUID: String,
    val language: String
)

@Keep
data class SyncDevicesBody(

    @SerializedName("uuid")
    val clientUUID: String,
    val language: String,

    @SerializedName("sync_code")
    val syncCode: String
)

@Keep
data class SignalMeasurementRequestBody(

    @SerializedName("plattform")
    val platform: String,
    val softwareVersionCode: Int,
    val softwareRevision: String,
    val softwareVersion: String,
    val time: Long,
    val timezone: String,

    @SerializedName("measurement_type_flag")
    val measurementTypeFlag: String,

    @SerializedName("uuid")
    val clientUUID: String,
    val location: SignalMeasurementLocationBody?
)

@Keep
data class SignalMeasurementLocationBody(
    val lat: Double,
    val long: Double,
    val provider: String,
    val speed: Float,
    val bearing: Float,
    val time: Long,
    val age: Long?,
    val accuracy: Float,
    val mock_location: Boolean,
    @Expose
    val altitude: Double,
    val satellites: Int?
)

@Keep
data class SignalMeasurementChunkBody(

    @SerializedName("test_uuid")
    val uuid: String?,

    @SerializedName("sequence_number")
    val sequenceNumber: Int,

    /**
     * Current time_ns of the client at the time of this submission. In case of resubmission, this time changes.
     * This property is only mandatory on sequence_number:0 time_ns:0 is defined as the moment that signal measurement is initiated,
     * either by the user or by network change.
     */
    @SerializedName("time_ns")
    val testStartTimeNanos: Long,

    @SerializedName("client_uuid")
    val clientUUID: String,

    @SerializedName("client_version")
    val clientVersion: String,

    @SerializedName("client_language")
    val clientLanguage: String,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("platform")
    val platform: String,

    @SerializedName("product")
    val product: String,

    @SerializedName("api_level")
    val apiLevel: String,

    @SerializedName("os_version")
    val osVersion: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("device")
    val device: String,

    @SerializedName("client_software_version")
    val clientSoftwareVersion: String,

    @SerializedName("network_type")
    val networkType: String,

    @SerializedName("wifi_supplicant_state")
    val wifiSupplicantState: String?,

    @SerializedName("wifi_supplicant_state_detail")
    val wifiSupplicantStateDetail: String?,

    @SerializedName("wifi_ssid")
    val wifiSSID: String?,

    @SerializedName("wifi_network_id")
    val wifiNetworkId: String?,

    @SerializedName("wifi_bssid")
    val wifiBSSID: String?,

    @SerializedName("telephony_network_operator")
    val telephonyNetworkOperator: String?,

    @SerializedName("telephony_network_is_roaming")
    val telephonyNetworkIsRoaming: String?,

    @SerializedName("telephony_network_country")
    val telephonyNetworkCountry: String?,

    @SerializedName("telephony_network_operator_name")
    val telephonyNetworkOperatorName: String?,

    @SerializedName("telephony_network_sim_operator_name")
    val telephonyNetworkSimOperatorName: String?,

    @SerializedName("telephony_network_sim_operator")
    val telephonyNetworkSimOperator: String?,

    @SerializedName("telephony_phone_type")
    val telephonyPhoneType: String?,

    @SerializedName("telephony_data_state")
    val telephonyDataState: String?,

    @SerializedName("telephony_apn")
    val telephonyAPN: String?,

    @SerializedName("telephony_network_sim_country")
    val telephonyNetworkSimCountry: String?,

    @SerializedName("submission_retry_count")
    val submissionRetryCount: Int,

    @SerializedName("test_status")
    val testStatus: String,

    @SerializedName("test_error_cause")
    val testErrorCause: String?,
    val geoLocations: List<TestLocationBody>?,
    val capabilities: CapabilitiesBody,

    val networkEvents: List<NetworkEventBody>?,

    val radioInfo: RadioInfoBody?,

    @SerializedName("android_permission_status")
    val permissionStatuses: List<PermissionStatusBody>?,

    val cellLocations: List<CellLocationBody>?
)

@Keep
data class NetworkEventBody(

    @SerializedName("event_type")
    val eventType: String,

    @SerializedName("event_message")
    val eventMessage: String?,

    @SerializedName("time_ns")
    val timeNanos: Long
)