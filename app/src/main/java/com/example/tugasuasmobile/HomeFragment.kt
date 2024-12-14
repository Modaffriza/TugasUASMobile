package com.example.tugasuasmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan9.R
import com.example.pertemuan9.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DprAdapter
    private lateinit var dprDao: DprDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dprDao = DprDatabase.getInstance(requireContext()).dprDao()

        adapter = DprAdapter { dpr ->
            dprDao.insert(dpr)
            Toast.makeText(context, "Added to favorite", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        fetchDprData()
    }

    private fun fetchDprData() {
        ApiClient.instance.getDpr().enqueue(object : Callback<List<Dpr>> {
            override fun onResponse(call: Call<List<Dpr>>, response: Response<List<Dpr>>) {
                if (response.isSuccessful) {
                    response.body()?.let { adapter.setData(it) }
                }
            }

            override fun onFailure(call: Call<List<Dpr>>, t: Throwable) {
                Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
