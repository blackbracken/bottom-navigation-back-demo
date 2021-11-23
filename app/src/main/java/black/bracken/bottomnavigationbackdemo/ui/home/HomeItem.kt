package black.bracken.bottomnavigationbackdemo.ui.home

import android.view.View
import black.bracken.bottomnavigationbackdemo.R
import black.bracken.bottomnavigationbackdemo.databinding.ItemHomeBinding
import com.xwray.groupie.viewbinding.BindableItem

class HomeItem(
    private val onClick: (Int) -> Unit
) : BindableItem<ItemHomeBinding>() {

    override fun getLayout() = R.layout.item_home

    override fun bind(binding: ItemHomeBinding, position: Int) {
        with(binding) {
            text.text = "Item $position"
            root.setOnClickListener { onClick(position) }
        }
    }

    override fun initializeViewBinding(view: View): ItemHomeBinding {
        return ItemHomeBinding.bind(view)
    }
}