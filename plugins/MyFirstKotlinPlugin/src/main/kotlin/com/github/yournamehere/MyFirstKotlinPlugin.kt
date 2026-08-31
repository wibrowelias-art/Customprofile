package com.github.yournamehere

import android.graphics.Color
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

import com.aliucord.annotations.AliucordPlugin
import com.aliucord.api.SettingsAPI
import com.aliucord.fragments.SettingsPage
import com.aliucord.plugins.Plugin
import com.aliucord.settings.SettingsTab

/**
 * LocalProfilePreview
 *
 * Everything stored here is local to this Aliucord installation.
 *
 * This plugin does NOT:
 *  - edit Discord account data
 *  - send profile changes to Discord
 *  - modify REST/gateway requests
 *  - grant Nitro
 *  - modify Discord entitlements
 *
 * It only supplies values for local UI rendering.
 */
@AliucordPlugin
class LocalProfilePreview : Plugin() {

    companion object {
        const val KEY_ENABLED = "enabled"
        const val KEY_USERNAME = "username"
        const val KEY_CREATION_DATE = "creation_date"

        const val KEY_BADGE_STAFF = "badge_staff"
        const val KEY_BADGE_PARTNER = "badge_partner"
        const val KEY_BADGE_HYPESQUAD = "badge_hypesquad"
        const val KEY_BADGE_BUG_HUNTER = "badge_bug_hunter"
        const val KEY_BADGE_DEVELOPER = "badge_developer"
        const val KEY_BADGE_MODERATOR = "badge_moderator"

        lateinit var instance: LocalProfilePreview
            private set

        fun settings(): SettingsAPI = instance.settings

        fun enabled(): Boolean =
            settings().getBool(KEY_ENABLED, false)

        fun username(): String =
            settings().getString(KEY_USERNAME, "")

        fun creationDate(): String =
            settings().getString(KEY_CREATION_DATE, "")

        fun badgeStaff(): Boolean =
            settings().getBool(KEY_BADGE_STAFF, false)

        fun badgePartner(): Boolean =
            settings().getBool(KEY_BADGE_PARTNER, false)

        fun badgeHypeSquad(): Boolean =
            settings().getBool(KEY_BADGE_HYPESQUAD, false)

        fun badgeBugHunter(): Boolean =
            settings().getBool(KEY_BADGE_BUG_HUNTER, false)

        fun badgeDeveloper(): Boolean =
            settings().getBool(KEY_BADGE_DEVELOPER, false)

        fun badgeModerator(): Boolean =
            settings().getBool(KEY_BADGE_MODERATOR, false)
    }

    init {
        instance = this

        settingsTab =
            SettingsTab(ProfileSettingsPage::class.java)
                .withArgs(settings)
    }

    override fun start(context: android.content.Context) {
        // Intentionally no network/account hooks.
    }

    override fun stop(context: android.content.Context) {
        // No hooks/listeners to remove.
    }
}


/**
 * Aliucord settings page.
 */
class ProfileSettingsPage(
    private val pluginSettings: SettingsAPI
) : SettingsPage() {

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        val context = view.context

        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 24, 32, 32)
        }

        fun title(text: String) {
            root.addView(
                TextView(context).apply {
                    this.text = text
                    textSize = 20f
                    setTextColor(Color.WHITE)

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    params.topMargin = 20
                    params.bottomMargin = 8

                    layoutParams = params
                }
            )
        }

        fun description(text: String) {
            root.addView(
                TextView(context).apply {
                    this.text = text
                    textSize = 14f
                    alpha = 0.75f

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    params.bottomMargin = 12

                    layoutParams = params
                }
            )
        }

        fun editText(
            hint: String,
            value: String,
            onChanged: (String) -> Unit
        ) {
            root.addView(
                EditText(context).apply {
                    this.hint = hint
                    setText(value)
                    setSingleLine(true)

                    setOnFocusChangeListener { _, hasFocus ->
                        if (!hasFocus) {
                            onChanged(text?.toString().orEmpty())
                        }
                    }

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    params.bottomMargin = 8

                    layoutParams = params
                }
            )
        }

        fun checkbox(
            text: String,
            checked: Boolean,
            onChanged: (Boolean) -> Unit
        ) {
            root.addView(
                CheckBox(context).apply {
                    this.text = text
                    this.isChecked = checked

                    setOnCheckedChangeListener { _, enabled ->
                        onChanged(enabled)
                    }

                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
            )
        }

        title("Local Profile Preview")

        description(
            "These values are local visual overrides. " +
                "They are not uploaded to Discord and do not change your account."
        )

        checkbox(
            "Enable local profile preview",
            pluginSettings.getBool(
                LocalProfilePreview.KEY_ENABLED,
                false
            )
        ) {
            pluginSettings.setBool(
                LocalProfilePreview.KEY_ENABLED,
                it
            )
        }

        title("Profile")

        editText(
            "Custom username",
            pluginSettings.getString(
                LocalProfilePreview.KEY_USERNAME,
                ""
            )
        ) {
            pluginSettings.setString(
                LocalProfilePreview.KEY_USERNAME,
                it
            )
        }

        editText(
            "Fake account creation date",
            pluginSettings.getString(
                LocalProfilePreview.KEY_CREATION_DATE,
                ""
            )
        ) {
            pluginSettings.setString(
                LocalProfilePreview.KEY_CREATION_DATE,
                it
            )
        }

        title("Local badges")

        checkbox(
            "Staff",
            pluginSettings.getBool(
                LocalProfilePreview.KEY_BADGE_STAFF,
                false
            )
        ) {
            pluginSettings.setBool(
                LocalProfilePreview.KEY_BADGE_STAFF,
                it
            )
        }

        checkbox(
            "Partner",
            pluginSettings.getBool(
                LocalProfilePreview.KEY_BADGE_PARTNER,
                false
            )
        ) {
            pluginSettings.setBool(
                LocalProfilePreview.KEY_BADGE_PARTNER,
                it
            )
        }

        checkbox(
            "HypeSquad",
            pluginSettings.getBool(
                LocalProfilePreview.KEY_BADGE_HYPESQUAD,
                false
            )
        ) {
            pluginSettings.setBool(
                LocalProfilePreview.KEY_BADGE_HYPESQUAD,
                it
            )
        }

        checkbox(
            "Bug Hunter",
            pluginSettings.getBool(
                LocalProfilePreview.KEY_BADGE_BUG_HUNTER,
                false
            )
        ) {
            pluginSettings.setBool(
                LocalProfilePreview.KEY_BADGE_BUG_HUNTER,
                it
            )
        }

        checkbox(
            "Active Developer",
            pluginSettings.getBool(
                LocalProfilePreview.KEY_BADGE_DEVELOPER,
                false
            )
        ) {
            pluginSettings.setBool(
                LocalProfilePreview.KEY_BADGE_DEVELOPER,
                it
            )
        }

        checkbox(
            "Moderator",
            pluginSettings.getBool(
                LocalProfilePreview.KEY_BADGE_MODERATOR,
                false
            )
        ) {
            pluginSettings.setBool(
                LocalProfilePreview.KEY_BADGE_MODERATOR,
                it
            )
        }

        title("Preview")

        description(
            "The renderer should display a small \"LOCAL PREVIEW\" " +
                "label whenever an override is active."
        )

        addView(root)
    }
}
