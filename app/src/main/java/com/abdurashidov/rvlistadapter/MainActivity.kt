package com.abdurashidov.rvlistadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.abdurashidov.rvlistadapter.adapter.UserAdapter
import com.abdurashidov.rvlistadapter.adapter.UserRvAdapter
import com.abdurashidov.rvlistadapter.databinding.ActivityMainBinding
import com.abdurashidov.rvlistadapter.models.User
import com.abdurashidov.rvlistadapter.utils.ItemTouchHelperAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list:ArrayList<User>
    private lateinit var userAdapter: UserAdapter
    private lateinit var userRvAdapter: UserRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        userAdapter= UserAdapter()
//        binding.rv.adapter=userAdapter

        loadData()
        userRvAdapter=UserRvAdapter(list, this)
        binding.rv.adapter=userRvAdapter
        touchHelper()
//        userAdapter.submitList(list)

    }

    private fun touchHelper() {
        val ItemTouchHelperAdapter=object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
            ): Int {
                val dragFlags=ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags=ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                userRvAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userRvAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }

        val touch=ItemTouchHelper(ItemTouchHelperAdapter)
        touch.attachToRecyclerView(binding.rv)

    }




    private fun loadData() {
        list= ArrayList()
        for (i in 0..100){
            list.add(User("Behzod $i", "+998911110106"))
            list.add(User("Bekzod $i", "+998905607294"))
            list.add(User("Sanjarbek $i", "+998935393111"))
        }
    }
}