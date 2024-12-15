package com.example.tugasuasmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasuasmobile.R
import com.example.tugasuasmobile.databinding.FragmentFavoriteBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DprAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefManager = PrefManager.getInstance(binding.root.context)
        val dprDao = DprDatabase.getInstance(requireContext()).dprDao()
        refresh()
//        dprDao.getAll().observe(viewLifecycleOwner) {
//            adapter.setData(it)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun refresh(){
            val database = DprDatabase.getInstance(binding.root.context)
            val dao = database!!.dprDao()!!
            val prefManager = PrefManager.getInstance(binding.root.context)
            GlobalScope.launch(Dispatchers.IO) {
                val data = prefManager.getData().filter { item ->
                    dao.getDpr(item._id) != null
                }

                withContext(Dispatchers.Main) {
                    val padapter = DprAdapter(
                        data
                    )
                    with(binding){
                        recyclerView.apply {
                            adapter = padapter
                            layoutManager= LinearLayoutManager(binding.root.context)
                        }
                    }
                }
            }
        }
    }

