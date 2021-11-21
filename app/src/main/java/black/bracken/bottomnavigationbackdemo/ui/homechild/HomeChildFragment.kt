package black.bracken.bottomnavigationbackdemo.ui.homechild

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import black.bracken.bottomnavigationbackdemo.databinding.FragmentHomeChildBinding

class HomeChildFragment : Fragment() {
    private lateinit var viewModel: HomeChildViewModel
    private var _binding: FragmentHomeChildBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(HomeChildViewModel::class.java)

        _binding = FragmentHomeChildBinding.inflate(inflater, container, false)

        with(binding) {
            button.setOnClickListener {
                val action =
                    HomeChildFragmentDirections.actionHomeChildFragmentToHomeGrandchildFragment()

                findNavController().navigate(action)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}