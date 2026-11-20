package kr.co.playbridge.sdk.ads

import kr.co.playbridge.commonsdk.internal.dto.AdErrorTypeSpec
import kotlin.reflect.KClass

enum class PlayBridgeError(
    val statusCode: Int,
    val statusMessage: String
) {

    // 💡 100번 에러 하나에 B 모듈의 100번 계열 인터페이스 3개를 모두 매핑합니다.
    ERROR_100(100, "100"),
    // 200번 계열도 동일하게 매핑
    ERROR_200(200, "200"),
    ERROR_300(300, "300"),
    ERROR_400(400, "400"),
    ERROR_500(500, "500");

    override fun toString(): String {
        return "PlayBridgeError(statusCode=$statusCode, statusMessage='$statusMessage')"
    }

    companion object {

        // 100 계열의 에러 타입들을 PlayBridgeError.ERROR_100 하나로 묶어서 매핑함
        private val typeMappingError100: Map<KClass<out AdErrorTypeSpec.ErrorType>, PlayBridgeError> = mapOf(
            AdErrorTypeSpec.ErrorType.INTERNAL_SDK_ERROR_100::class to ERROR_100,
            AdErrorTypeSpec.ErrorType.THIRD_PARTY_SDK_ERROR_100::class to ERROR_100,
            AdErrorTypeSpec.ErrorType.NETWORK_ERROR_100::class to ERROR_100
        )

        // 200 계열의 에러 타입들을 PlayBridgeError.ERROR_200 하나로 묶어서 매핑함
        private val typeMappingError200: Map<KClass<out AdErrorTypeSpec.ErrorType>, PlayBridgeError> = mapOf(
            AdErrorTypeSpec.ErrorType.INTERNAL_SDK_ERROR_200::class to ERROR_200,
            AdErrorTypeSpec.ErrorType.THIRD_PARTY_SDK_ERROR_200::class to ERROR_200,
            AdErrorTypeSpec.ErrorType.NETWORK_ERROR_200::class to ERROR_200
        )

        // 300 계열의 에러 타입들을 PlayBridgeError.ERROR_300 하나로 묶어서 매핑함
        private val typeMappingError300: Map<KClass<out AdErrorTypeSpec.ErrorType>, PlayBridgeError> = mapOf(
            AdErrorTypeSpec.ErrorType.INTERNAL_SDK_ERROR_300::class to ERROR_300,
            AdErrorTypeSpec.ErrorType.THIRD_PARTY_SDK_ERROR_300::class to ERROR_300,
            AdErrorTypeSpec.ErrorType.NETWORK_ERROR_300::class to ERROR_300
        )

        // 400 계열의 에러 타입들을 PlayBridgeError.ERROR_400 하나로 묶어서 매핑함
        private val typeMappingError400: Map<KClass<out AdErrorTypeSpec.ErrorType>, PlayBridgeError> = mapOf(
            AdErrorTypeSpec.ErrorType.INTERNAL_SDK_ERROR_400::class to ERROR_400,
            AdErrorTypeSpec.ErrorType.THIRD_PARTY_SDK_ERROR_400::class to ERROR_400,
            AdErrorTypeSpec.ErrorType.NETWORK_ERROR_400::class to ERROR_400
        )

        // 500 계열의 에러 타입들을 PlayBridgeError.ERROR_500 하나로 묶어서 매핑함
        private val typeMappingError500: Map<KClass<out AdErrorTypeSpec.ErrorType>, PlayBridgeError> = mapOf(
            AdErrorTypeSpec.ErrorType.INTERNAL_SDK_ERROR_500::class to ERROR_500,
            AdErrorTypeSpec.ErrorType.THIRD_PARTY_SDK_ERROR_500::class to ERROR_500,
            AdErrorTypeSpec.ErrorType.NETWORK_ERROR_500::class to ERROR_500
        )

        // 💡 모든 분리된 맵을 하나의 리스트로 통합하여 from 메서드에서 순회 기반 검색을 마련
        private val allMappings = listOf(
            typeMappingError100,
            typeMappingError200,
            typeMappingError300,
            typeMappingError400,
            typeMappingError500
        )

        /**
         * AdapterSDK 모듈에서 넘어온 AdErrorTypeSpec을 CoreSDK 모듈의 PlayBridgeError로 변환
         */
        fun from(bError: AdErrorTypeSpec): PlayBridgeError {
            val incomingErrorType = bError.errorType

            // 1. 모든 매핑 리스트를 순회하면서, C모듈이 던진 errorType 인스턴스를 포함하는 KClass 키가 있는지 찾습니다.
            for (mapping in allMappings) {
                val matchedEnum = mapping.entries.find { (kClass, _) ->
                    kClass.isInstance(incomingErrorType)
                }?.value

                if (matchedEnum != null) {
                    return matchedEnum
                }
            }

            // 2. 만약 매핑 테이블에서 일치하는 타입을 전혀 찾지 못했다면 기본 fallback 에러를 리턴합니다.
            return ERROR_500
        }
    }
}
