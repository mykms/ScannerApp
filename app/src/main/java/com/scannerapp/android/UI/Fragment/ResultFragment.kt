package com.scannerapp.android.UI.Fragment

import android.os.Bundle
import android.view.View
import com.scannerapp.android.Network.ApiMethods
import com.scannerapp.android.Constants
import com.scannerapp.android.Model.Product
import com.scannerapp.android.Model.ProductDetail
import com.scannerapp.android.Model.ProductResult
import com.scannerapp.android.Presenter.SearchPresenter
import com.scannerapp.android.R
import com.scannerapp.android.View.SearchView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : BaseFragment(), SearchView {
    private var barCode: String = ""
    override fun getArgs(args: Bundle?) {
        barCode = args?.getString(Constants.EXTRA_CODE, "").toString()
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_result
    }

    override fun initComponents(view: View) {
        if (!barCode.isNullOrEmpty()) {
            val presenter = SearchPresenter(this, ApiMethods.getInstance())
            presenter.searchProduct(Product(barCode))
        }
    }

    override fun onError(message: String) {
        super.onMessage(message)
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            pb_progress?.visibility = View.VISIBLE
        } else {
            pb_progress?.visibility = View.GONE
        }
    }

    override fun onResult(productResult: ProductResult?) {
        if (productResult?.result == 0) {
            val prodCount = productResult.data.size
            if (prodCount > 0) {
                val prodDetail: ProductDetail = productResult.data[0]
                tv_prod_name.text = "всего товаров: $prodCount \n" + prodDetail.name

                if (prodDetail.images.isNotEmpty()) {
                    Picasso.get()
                            .load(ApiMethods.IMAGE_URL + prodDetail.images[0])
                            .placeholder(R.drawable.vector_ic_picture)
                            .error(R.drawable.vector_ic_picture)
                            .into(iv_img)
                }
            }
        } else {
            super.onMessage("Ошибка: Товар не найден")
        }
    }
}
