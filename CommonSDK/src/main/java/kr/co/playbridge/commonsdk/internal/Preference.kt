package kr.co.playbridge.commonsdk.internal


import android.content.Context
import android.content.SharedPreferences
import kr.co.playbridge.commonsdk.api.utils.LogUtil
import androidx.core.content.edit

class Preference private constructor(){

    companion object {

        private fun getPreference(context: Context): SharedPreferences {
            return context.getSharedPreferences(Const.PREFERENCE_NAME, Context.MODE_PRIVATE)
        }

        /**
         * SDK-90: 지면(placementUid)별 캐시 격리를 위한 키 네임스페이스 헬퍼.
         * 한 지면에 여러 애드폼/인스턴스가 함께 있을 때 설정이 서로 덮이지 않도록,
         * 지면 종속 값(mediations, ad_reload_sec, config_reload_sec, timeout_sec, enable_debug)은
         * baseKey + "__" + placementUid 형태로 저장/조회한다.
         * placementUid가 없으면 하위호환을 위해 기존 전역 키를 그대로 사용한다.
         */
        fun keyForPlacement(baseKey: String?, placementUid: String?): String? {
            if (placementUid == null || placementUid.isEmpty()) {
                return baseKey
            }
            return baseKey + "__" + placementUid
        }

        fun setBoolean(context: Context, key: String?, value: Boolean) {
            val sharedPreferences = getPreference(context)
            sharedPreferences.edit(commit = true) {
                putBoolean(key, value)
            }
        }

        fun setInt(context: Context, key: String?, value: Int) {
            val sharedPreferences = getPreference(context)
            sharedPreferences.edit(commit = true) {
                putInt(key, value)
            }
        }

        fun setLong(context: Context, key: String?, value: Long) {
            val sharedPreferences = getPreference(context)
            sharedPreferences.edit(commit = true) {
                putLong(key, value)
            }
        }

        fun setFloat(context: Context, key: String?, value: Float) {
            val sharedPreferences = getPreference(context)
            sharedPreferences.edit(commit = true) {
                putFloat(key, value)
            }
        }

        fun setString(context: Context, key: String?, value: String?) {
            val sharedPreferences = getPreference(context)
            sharedPreferences.edit {
                putString(key, value)
            }
        }

        fun setStringSet(context: Context, key: String?, value: MutableSet<String?>?) {
            val sharedPreferences = getPreference(context)
            sharedPreferences.edit {
                putStringSet(key, value)
            }
        }

        fun getBoolean(context: Context, key: String?): Boolean {
            return getBoolean(context, key, false)
        }

        fun getBoolean(context: Context, key: String?, defaultValue: Boolean): Boolean {
            return getPreference(context).getBoolean(key, defaultValue)
        }

        fun getInt(context: Context, key: String?): Int {
            return getInt(context, key, Const.DEFAULT_VALUE_INT)
        }

        fun getInt(context: Context, key: String?, defaultValue: Int): Int {
            return getPreference(context).getInt(key, defaultValue)
        }

        fun getLong(context: Context, key: String?): Long {
            return getLong(context, key, Const.DEFAULT_VALUE_LONG)
        }

        fun getLong(context: Context, key: String?, defaultValue: Long): Long {
            return getPreference(context).getLong(key, defaultValue)
        }

        fun getFloat(context: Context, key: String?): Float {
            return getFloat(context, key, Const.DEFAULT_VALUE_FLOAT)
        }

        fun getFloat(context: Context, key: String?, defaultValue: Float): Float {
            return getPreference(context).getFloat(key, defaultValue)
        }

        fun getString(context: Context, key: String?): String? {
            return getString(context, key, Const.DEFAULT_VALUE_STRING)
        }

        fun getString(context: Context, key: String?, defaultValue: String?): String? {
            return getPreference(context).getString(key, defaultValue)
        }

        fun getStringSet(context: Context, key: String?): MutableSet<String?>? {
            return getStringSet(context, key, Const.DEFAULT_VALUE_STRING_NULL)
        }

        fun getStringSet(
            context: Context,
            key: String?,
            defaultValue: MutableSet<String?>?
        ): MutableSet<String?>? {
            val sharedPreferences = getPreference(context)
            return sharedPreferences.getStringSet(key, defaultValue)
        }

        fun deleteKey(context: Context, key: String?) {
            val sharedPreferences = getPreference(context)
            sharedPreferences.edit(commit = true) {
                remove(key)
            }
        }

        fun clear(context: Context) {
            val sharedPreferences = getPreference(context)
            sharedPreferences.edit(commit = true) {
                clear()
            }
        }

        fun getAll(context: Context) {
            val sharedPreferences = getPreference(context)

            val keys = sharedPreferences.getAll()
            for (entry in keys.entries) {
                LogUtil.d(entry.key + " : " + entry.value.toString())
            }
        }
    }
}
