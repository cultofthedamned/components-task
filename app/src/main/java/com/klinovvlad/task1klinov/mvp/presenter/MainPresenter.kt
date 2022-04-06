package com.klinovvlad.task1klinov.mvp.presenter

import com.klinovvlad.task1klinov.mvp.model.Item

class MainPresenter(private val mainView: ItemView) : MainModel.FetchItems {

    private val itemModel: ItemModel
    private var itemModelList: List<Item>? = null

    init {
        itemModel = MainModel(this)
        itemModel.getItems()
    }

    override fun showItems(data: List<Item>) {
        itemModelList = data
        mainView.showItemView(itemModelList!!)
    }

}