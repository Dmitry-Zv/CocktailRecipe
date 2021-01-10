package by.zharikov.cocktails.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerHome.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerHome.adapter = HomeAdapter(MainWindow.get(), requireContext())
    }
//
//    override fun viewNextFragment(holder: View, position: Int) {
//        when (position) {
//            0 -> findNavController().navigate(R.id.action_homeFragment_to_navigation_fragment_by_first_letter)
//            1 -> findNavController().navigate(R.id.action_homeFragment_to_cocktailByNameFragment)
//            2 -> findNavController().navigate(R.id.action_homeFragment_to_cocktailByIngredientFragment)
//        }
//    }
}