package com.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.databindingdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var list: ArrayList<Employee> = arrayListOf()
    private var adapter: EmployeeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        val person = Person("Mahipal","Engineer")
        binding.setVariable(BR.person,person)
        binding.executePendingBindings()

        val recyclerView = binding.recyclerViewUser
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        adapter = EmployeeAdapter(list)
        recyclerView.adapter = adapter

        getEmployees()
    }

    private fun getEmployees() {
        val call = RetrofitClient.service?.employees

        call?.enqueue(object : Callback<EmployeeDBResponse>{
            override fun onFailure(call: Call<EmployeeDBResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<EmployeeDBResponse>, response: Response<EmployeeDBResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    if (list.isNotEmpty()) {
                        list.clear()
                    }
                    list.addAll(response.body()?.data?.toMutableList()?:ArrayList())
                    adapter?.notifyDataSetChanged()
                }
            }
        })
    }
}
