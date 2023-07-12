package net.uniquecomputer.spendly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.uniquecomputer.spendly.databinding.FragmentAddRecordsBinding

class AddRecordsFragment : BottomSheetDialogFragment() {

    private var binding: FragmentAddRecordsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddRecordsBinding.inflate(inflater, container, false)
        return binding?.root
    }


}