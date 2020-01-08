package com.databindingdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(var list : ArrayList<Employee>): RecyclerView.Adapter<EmployeeAdapter.ViewHolderBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBinding {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
            R.layout.list_person_details,parent,false)
        return ViewHolderBinding(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderBinding, position: Int) {
        holder.binding.setVariable(BR.employee,list[position])
        holder.binding.executePendingBindings()
    }

    inner class ViewHolderBinding(var binding:ViewDataBinding): RecyclerView.ViewHolder(binding.root)
}