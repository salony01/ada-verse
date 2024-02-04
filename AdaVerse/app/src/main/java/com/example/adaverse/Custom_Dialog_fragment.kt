package com.example.adaverse

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.example.adaverse.databinding.FragmentCustomDialogBinding

class Custom_Dialog_fragment : DialogFragment() {


    //private lateinit var binding: FragmentCustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView: View =inflater.inflate(R.layout.fragment_custom__dialog,container,false)
        val myInterface: MyInterface = activity as MyInterface
        rootView.findViewById<Button>(R.id.submitBtn).setOnClickListener {

            val selectedId=rootView.findViewById<RadioGroup>(R.id.genre_radio_grp).checkedRadioButtonId
            val radio = rootView.findViewById<RadioButton>(selectedId)
            var result=radio.text.toString()

            myInterface.transferredMessage(result)


            dismiss()
        }
        return rootView
    }


}