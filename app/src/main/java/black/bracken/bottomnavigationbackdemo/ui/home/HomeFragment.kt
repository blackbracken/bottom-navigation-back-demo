package black.bracken.bottomnavigationbackdemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import black.bracken.bottomnavigationbackdemo.MainViewModel
import black.bracken.bottomnavigationbackdemo.R
import black.bracken.bottomnavigationbackdemo.databinding.FragmentHomeBinding
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding.recycler) {
            val items = (0 until 30).map {
                HomeItem {
                    val action = HomeFragmentDirections.actionHomeFragmentToHomeChildFragment()
                    findNavController().navigate(action)
                }
            }
            adapter = GroupieAdapter().apply {
                addAll(items)
            }

            val linearLayoutManager = LinearLayoutManager(requireContext())
            layoutManager = linearLayoutManager

            val divider = DividerItemDecoration(requireContext(), linearLayoutManager.orientation)
            addItemDecoration(divider)
        }

        lifecycleScope.launch {
            mainViewModel.reselectedItemOnRoot
                .filter { it.isHome() }
                .collect {
                    binding.recycler.smoothScrollToPosition(0)
                }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}