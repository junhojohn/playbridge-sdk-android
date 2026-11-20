package kr.co.playbridge.commonsdk.internal.dto

interface AdErrorTypeSpec {
    val statusCode: Int
    val statusMessage: String
    val errorType: ErrorType

    interface ErrorType {
        object INTERNAL_SDK_ERROR_100 : ErrorType
        object INTERNAL_SDK_ERROR_200 : ErrorType
        object INTERNAL_SDK_ERROR_300 : ErrorType
        object INTERNAL_SDK_ERROR_400 : ErrorType
        object INTERNAL_SDK_ERROR_500 : ErrorType

        object THIRD_PARTY_SDK_ERROR_100 : ErrorType
        object THIRD_PARTY_SDK_ERROR_200 : ErrorType
        object THIRD_PARTY_SDK_ERROR_300 : ErrorType
        object THIRD_PARTY_SDK_ERROR_400 : ErrorType
        object THIRD_PARTY_SDK_ERROR_500 : ErrorType

        object NETWORK_ERROR_100 : ErrorType
        object NETWORK_ERROR_200 : ErrorType
        object NETWORK_ERROR_300 : ErrorType
        object NETWORK_ERROR_400 : ErrorType
        object NETWORK_ERROR_500 : ErrorType
    }
}