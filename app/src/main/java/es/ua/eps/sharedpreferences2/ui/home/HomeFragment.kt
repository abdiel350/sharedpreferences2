package es.ua.eps.sharedpreferences2.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import es.ua.eps.sharedpreferences2.R

class HomeFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var previewText: TextView
    private lateinit var previewButton: Button
    private lateinit var closeButton: Button
    private lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar la vista del fragmento
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Inicializar los elementos de la vista
        previewText = rootView.findViewById(R.id.previewtext)
        previewButton = rootView.findViewById(R.id.previewboton)
        closeButton = rootView.findViewById(R.id.closebotton)
        editText = rootView.findViewById(R.id.editText)

        // Obtener las preferencias compartidas
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Establecer el listener del botón Preview
        previewButton.setOnClickListener {
            val textToPreview = editText.text.toString()
            if (textToPreview.isBlank()) {
                Toast.makeText(requireContext(), "Por favor introduzca un texto", Toast.LENGTH_SHORT).show()
            } else {
                updatePreviewText(textToPreview)  // Pasar el texto a la función
            }
        }

        // Establecer el listener del botón Close
        closeButton.setOnClickListener {
            requireActivity().finish()
        }

        return rootView
    }

    // Función para actualizar el texto de vista previa
    private fun updatePreviewText(textToPreview: String) {
        // Leer las preferencias compartidas
        val fontSize = sharedPreferences.getString("font_size", "16")?.toIntOrNull() ?: 16
        val textColor = sharedPreferences.getString("text_color", "#000000") ?: "#000000"
        val bgColor = sharedPreferences.getString("background_color", "#FFFFFF") ?: "#FFFFFF"
        val alpha = sharedPreferences.getInt("alpha", 100) / 100f
        val rotation = sharedPreferences.getInt("rotation", 0).toFloat()

        val isBold = sharedPreferences.getBoolean("bold_text", false)
        val isItalic = sharedPreferences.getBoolean("italic_text", false)

        // Determinar el estilo del texto
        val textStyle = when {
            isBold && isItalic -> android.graphics.Typeface.BOLD_ITALIC
            isBold -> android.graphics.Typeface.BOLD
            isItalic -> android.graphics.Typeface.ITALIC
            else -> android.graphics.Typeface.NORMAL
        }

        // Aplicar los valores a la vista
        previewText.text = textToPreview
        previewText.textSize = fontSize.toFloat()
        previewText.setTextColor(android.graphics.Color.parseColor(textColor))
        previewText.setBackgroundColor(android.graphics.Color.parseColor(bgColor))
        previewText.alpha = alpha
        previewText.rotation = rotation
        previewText.setTypeface(null, textStyle)

        // Mostrar el texto de vista previa
        previewText.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}