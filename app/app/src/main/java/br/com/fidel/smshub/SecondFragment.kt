package br.com.fidel.smshub

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.fidel.smshub.utils.SettingsManager

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    protected lateinit var settingsManager: SettingsManager
    protected lateinit var mainActivity: MainActivity



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_second, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsManager = this.activity?.let { SettingsManager(it) }!!

        mainActivity = this.activity as MainActivity
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = this.activity as MainActivity
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        val btnSave: Button = view.findViewById(R.id.btnSave)
        val txtSendURL: EditText = view.findViewById(R.id.textSendURL)
        val txtStatusURL: EditText = view.findViewById(R.id.textStatusURL)
        val txtReceiveURL: EditText = view.findViewById(R.id.textReceiveURL)
        val txtInterval: EditText = view.findViewById(R.id.textInterval)
        val txtDeviceId: EditText = view.findViewById(R.id.textDeviceId)
        val switchIsEnabled: Switch = view.findViewById(R.id.switchIsEnabled)

        txtInterval.setText(settingsManager.interval.toString())
        switchIsEnabled.isChecked = settingsManager.isSendEnabled
        txtSendURL.setText(settingsManager.sendURL)
        txtReceiveURL.setText(settingsManager.receiveURL)
        txtStatusURL.setText(settingsManager.statusURL)
        txtDeviceId.setText(settingsManager.deviceId)

        //save
        btnSave.setOnClickListener {
            settingsManager.setSettings(
                switchIsEnabled.isChecked,
                txtInterval.text.toString().toInt(),
                txtSendURL.text.toString(),
                txtReceiveURL.text.toString(),
                txtStatusURL.text.toString(),
                txtDeviceId.text.toString()
            )
        }

        //save
        switchIsEnabled.setOnClickListener {
            var ok = true
            //if enabling first validate everything
            if (switchIsEnabled.isChecked) {
                if (txtInterval.text.toString() == "" ||
                    txtSendURL.text.toString() == "" ||
                    txtDeviceId.text.toString() == "" ||
                    txtStatusURL.text.toString() == "" ||
                    txtReceiveURL.text.toString() == ""
                ) {
                    switchIsEnabled.isChecked = false
                    Toast.makeText(activity, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show()
                    ok = false
                }

            }

            if (ok) {
                Log.d("--->switchIsEnabled", switchIsEnabled.isChecked.toString())
                settingsManager.setSettings(
                    switchIsEnabled.isChecked,
                    txtInterval.text.toString().toInt(),
                    txtSendURL.text.toString(),
                    txtReceiveURL.text.toString(),
                    txtStatusURL.text.toString(),
                    txtDeviceId.text.toString()
                )
            }
            mainActivity.updateTimer()
        }
    }
}
