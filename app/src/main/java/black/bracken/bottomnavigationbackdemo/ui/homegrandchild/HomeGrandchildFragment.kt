package black.bracken.bottomnavigationbackdemo.ui.homegrandchild

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import black.bracken.bottomnavigationbackdemo.databinding.FragmentHomeGrandchildBinding

class HomeGrandchildFragment : Fragment() {
    private lateinit var viewModel: HomeGrandchildViewModel
    private var _binding: FragmentHomeGrandchildBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(HomeGrandchildViewModel::class.java)

        _binding = FragmentHomeGrandchildBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}