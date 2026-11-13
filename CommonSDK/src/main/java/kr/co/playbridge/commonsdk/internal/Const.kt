package kr.co.playbridge.commonsdk.internal

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale


class Const private constructor(){
    companion object {
        const val SERVICE_NAME: String = "PLAYBRIDGE"

        val LOG_TAG: String = SERVICE_NAME + "-SDK"

        val PREFERENCE_NAME: String = LOG_TAG

        const val BIG_BRACKET_LEFT: String = "["

        const val BIG_BRACKET_RIGHT: String = "]"

        const val QUESTION_MARK: String = "?"

        const val SEMI_COLON: String = ":"

        const val COMMA: String = ","

        const val PREF_SEPARATOR: String = "||"

        const val BLANK: String = " "

        const val EMPTY: String = ""

        const val EMPTY_STR: String = ""

        const val NEXT_LINE: String = "\n"

        const val AMPERSAND: String = "&"

        const val EQUAL: String = "="

        const val BRACKET_LEFT: String = "("

        const val BRACKET_RIGHT: String = ")"

        const val KEY_PAGE: String = "page"

        const val KEY_ACTION: String = "action"

        const val SLASH: String = "/"

        const val UNDERSCORE: String = "_"

        const val PUBLISHER_UID: String = "kr.co.playbridge.PUBLISHER_UID"

        const val KEY_HEX_FORMAT: String = "%02x"

        const val KEY_SHA_256: String = "SHA-256"

        val DATE_FORMAT_DETAIL: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        val DATE_FORMAT: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        val DATE_FORMAT2: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd")

        val DATE_FORMAT_HH_MM: SimpleDateFormat = SimpleDateFormat("HH:mm")
        val DATE_FORMAT_KOR: SimpleDateFormat = SimpleDateFormat("yyyy년 MM월 dd일까지", Locale.KOREAN)

        val PLUS_DECIMAL_FORMAT: DecimalFormat = DecimalFormat("+#,###")

        val SIMPLE_DECIMAL_FORMAT: DecimalFormat = DecimalFormat("#,###")

        const val REG_EXP_EMAIL: String = "^[a-z0-9\\.\\-_]+@([a-z0-9\\-]+\\.)+[a-z]{2,6}$"
        const val REG_EXP_MOBILE_NUMBER: String = "^01(?:0|1|[6-9])\\d{7,8}$"

        const val DEFAULT_VALUE_BOOLEAN: Boolean = false

        const val DEFAULT_RELOAD_SEC: Int = 30

        const val DEFAULT_CONFIG_RELOAD_SEC: Int = 3600

        const val DEFAULT_TIMEOUT_SEC: Int = 3

        const val DEFAULT_AD_LOAD_TIMEOUT_SEC: Int = 10

        const val DEFAULT_RETRY_NETWORK_CNT: Int = 3

        val DEFAULT_TIMEOUT_MILLISEC: Int = DEFAULT_TIMEOUT_SEC * 1000

        val DEFAULT_VALUE_INT: Int = -1

        val DEFAULT_VALUE_LONG: Long = -1L

        val DEFAULT_VALUE_FLOAT: Float = -1f

        const val DEFAULT_VALUE_STRING: String = ""

        val DEFAULT_VALUE_STRING_NULL: MutableSet<String?>? = null

        const val DEFAULT_ZERO: Int = 0

        const val DESC: String = "desc"

        const val ASC: String = "asc"


        const val KEY_APP_NAME: String = "app_name"

        const val KEY_APP_VERSION: String = "app_version"

        const val KEY_PACKAGE_NAME: String = "app_bundle"

        const val KEY_STORE_URL: String = "app_storeurl"

        const val KEY_USER_AGENT: String = "app_ua"

        const val KEY_AID: String = "aid"

        const val KEY_IFA: String = "ifa" // IFA는 GOOGLE AD ID에 해당

        const val KEY_IFV: String = "ifv" // IFV는 GOOGLE APP SET ID에 해당

        const val KEY_IFV_SCOPE: String = "ifv_scope" // IFV는 GOOGLE APP SET ID에 해당

        const val KEY_IS_IFA: String = "is_ifa" // ID가 IFA인지 IFV인지 여부. IFA이면 true, IFV이면 false

        const val KEY_DO_NOT_TRACK: String = "dnt"

        const val KEY_LIMIT_AD_TRACKING: String = "lmt"

        const val KEY_IP: String = "ip"

        const val KEY_MANUFACTURER: String = "manufacturer"

        const val KEY_MODEL: String = "model"

        const val KEY_OS_TYPE: String = "os_type"

        const val KEY_OS_VERSION: String = "os_version"

        const val KEY_HW_VERSION: String = "hw_version"

        const val KEY_IS_EMULATOR: String = "is_emulator"

        const val KEY_TARGET_SDK_VERSION: String = "target_sdk_version"

        const val KEY_SDK_VERSION: String = "sdk_version"

        const val KEY_IS_EFFECTIVE_RENDERING_AVAILABLE: String = "is_effective_rendering_available"

        const val KEY_TELECOM_CARRIER: String = "telecom_carrier"

        const val KEY_NETWORK_CONNECTION_INFO: String = "network_connection_info"

        const val KEY_HEIGHT: String = "height"

        const val KEY_WIDTH: String = "width"

        const val KEY_PIXEL_PER_INCH: String = "ppi"

        const val KEY_DENSITY: String = "pxratio"

        const val KEY_LANGUAGE: String = "language"

        const val KEY_PUBLISHER_UID: String = "publisher_uid"

        const val KEY_PLACEMENT_UID: String = "placement_uid"

        const val KEY_AD_RELOAD_SEC: String = "ad_reload_sec"

        const val KEY_CONFIG_RELOAD_SEC: String = "config_reload_sec"

        const val KEY_TIMEOUT_SEC: String = "timeout_sec"

        /** 광고 로드 워치독 제한 시간(초). 설정 API 통신 타임아웃(timeout_sec)과는 별개다.  */
        const val KEY_AD_LOAD_TIMEOUT_SEC: String = "ad_load_timeout_sec"

        const val KEY_ENABLE_DEBUG: String = "enable_debug"

        const val KEY_MEDIATIONS: String = "mediations"

        const val KEY_TYPE: String = "type"

        const val KEY_NETWORK: String = "network"

        const val KEY_AD_UNIT: String = "ad_unit"

        const val KEY_EXTENSION: String = "extension"

        const val KEY_EXTENSION1: String = "extension1"

        const val KEY_EXTENSION2: String = "extension2"
    }
}
