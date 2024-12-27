package es.ua.eps.sharedpreferences2.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SeekBarPreference
import androidx.preference.SwitchPreference
import androidx.preference.CheckBoxPreference
import es.ua.eps.sharedpreferences2.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        updatePreferenceSummaries()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key?.let {
            updatePreferenceSummary(findPreference(it))
        }
    }

    private fun updatePreferenceSummaries() {
        listOf("font_size", "text_color", "background_color", "alpha", "rotation", "bold_text", "italic_text").forEach {
            updatePreferenceSummary(findPreference(it))
        }
    }
    private fun updatePreferenceSummary(preference: Preference?) {
        when (preference) {
            is ListPreference -> preference.summary = preference.entry
            is SeekBarPreference -> {
                val value = when (preference.key) {
                    "alpha" -> String.format("%80s", preference.value / 100f)
                    "rotation" -> String.format("%80d", preference.value)
                    else -> null
                }
                value?.let {
                    preference.summary = it // Asigna el valor formateado
                }
            }
            is SwitchPreference -> {
                preference.summary = if (preference.isChecked) "Bold Text" else "Text is not bold"
            }
            is CheckBoxPreference -> {
                preference.summary = if (preference.isChecked) "Italic Text" else "Text is not italic"
            }
        }
    }

}
