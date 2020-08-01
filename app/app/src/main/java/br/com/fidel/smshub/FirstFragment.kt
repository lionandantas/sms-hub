package br.com.fidel.smshub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    lateinit var textMainLog: TextView
    protected lateinit var mainActivity: MainActivity
    var viewCreated = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainActivity = this.activity as MainActivity

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = this.activity as MainActivity
        textMainLog = view.findViewById(R.id.textMainLog)
        var buttonClearLog = view.findViewById(R.id.buttonClearLog) as Button
        buttonClearLog.setOnClickListener {
            this.textMainLog.text = ""
        }

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        viewCreated = true
    }
}
