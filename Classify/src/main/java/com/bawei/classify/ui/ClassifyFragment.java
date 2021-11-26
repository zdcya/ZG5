package com.bawei.classify.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bawei.classify.R;
import com.bawei.classify.entity.Goods;
import com.bawei.classify.entity.GoodsType;
import com.bawei.classify.viewmodel.ClassifyCenterViewModel;
import com.bawei.mvvmcore.view.BaseMVVMFragment;
import com.bawei.net.procotol.BaseRespEntity;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassifyFragment extends BaseMVVMFragment<ClassifyCenterViewModel, ViewDataBinding> {

    private View banView;
    ArrayList<GoodsType> goodsTypes;
    ArrayList<GoodsType> goodsTypes2;
    private RecyclerView rvFirst;
    ArrayList<Goods> goodsList;
    int index = 16;
    private View pitchOn;
    private TextView txtFirst;
    private RecyclerView rvSecond;
    private String keyword = "推荐";
    private String type = "推荐";

    @Override
    protected Integer getLayoutId() {
        return R.layout.classify_fragment;
    }

    @Override
    protected ClassifyCenterViewModel createViewModel() {
        return new ClassifyCenterViewModel(this);
    }


    @Override
    protected void prepareSetVars(HashMap mMap) {

    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected void initEnv() {

    }

    @Override
    protected void initView(View view) {

        rvFirst = view.findViewById(R.id.rv_first);
        rvFirst.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSecond = view.findViewById(R.id.rv_second);
        rvSecond.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    @Override
    protected void loadData() {

        First_classify();
        Second_classify();


    }

    private void First_classify() {

        mViewModel.getGoodsType(0).observe(this, new Observer<BaseRespEntity<List<GoodsType>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<GoodsType>> listBaseRespEntity) {

                if (listBaseRespEntity.getData() != null && listBaseRespEntity.getCode() == 200) {

                    goodsTypes = new ArrayList<>();

                    for (int i = 0; i < listBaseRespEntity.getData().size(); i++) {

                        GoodsType goodsType = listBaseRespEntity.getData().get(i);

                        goodsTypes.add(goodsType);
                    }

                    FirstclassifyAdapter firstclassifyAdapter = new FirstclassifyAdapter(goodsTypes);

                    rvFirst.setAdapter(firstclassifyAdapter);


                    firstclassifyAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

                            type = goodsTypes.get(position).getCategory_name();
                            keyword  = goodsTypes.get(position).getCategory_name();

                            Second_classify();

                        }
                    });

                } else {

                    ToastUtils.showLong("失败");

                }
            }
        });
    }

    private void Second_classify() {

//        mViewModel.getGoodsType(index).observe(this, new Observer<BaseRespEntity<List<GoodsType>>>() {
//            @Override
//            public void onChanged(BaseRespEntity<List<GoodsType>> listBaseRespEntity) {
//
//                if (listBaseRespEntity.getData() != null && listBaseRespEntity.getCode() == 200) {
//
//                    goodsTypes2 = new ArrayList<>();
//
//                    for (int i = 0; i < listBaseRespEntity.getData().size(); i++) {
//
//                        GoodsType goodsType = listBaseRespEntity.getData().get(i);
//
//                        goodsTypes2.add(goodsType);
//                    }
//
//                    SecondclassifyAdapter secondclassifyAdapter = new SecondclassifyAdapter(goodsTypes2);
//
//                    rvSecond.setAdapter(secondclassifyAdapter);
//
//
//                } else {
//
//                    ToastUtils.showLong("失败");
//
//                }
//            }
//        });

        mViewModel.getGoods(keyword,type).observe(this, new Observer<BaseRespEntity<List<Goods>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<Goods>> listBaseRespEntity) {

                if (listBaseRespEntity.getData()!=null && listBaseRespEntity.getCode() == 200){

                    goodsList = new ArrayList<>();

                    for (int i = 0; i <listBaseRespEntity.getData().size() ; i++) {

                        Goods goods = listBaseRespEntity.getData().get(i);

                        goodsList.add(goods);
                    }

                    SecondclassifyAdapter secondclassifyAdapter = new SecondclassifyAdapter(goodsList);

                    rvSecond.setAdapter(secondclassifyAdapter);


                }else {

                    ToastUtils.showLong("失败");
                }
            }
        });
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return banView = inflater.inflate(R.layout.classify_fragment, container, false);
    }

    public class FirstclassifyAdapter extends BaseQuickAdapter<GoodsType, BaseViewHolder> {


        public FirstclassifyAdapter(@Nullable List<GoodsType> data) {
            super(R.layout.item_first, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsType goodsType) {

            baseViewHolder.setText(R.id.txt_first, goodsType.getCategory_name());

        }
    }


    public class SecondclassifyAdapter extends BaseQuickAdapter<Goods,BaseViewHolder> {


        public SecondclassifyAdapter( @Nullable List<Goods> data) {
            super(R.layout.classify_item_second, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, Goods goods) {


            baseViewHolder.setText(R.id.txt_second,goods.getCategoryName());

            Glide.with(getActivity()).load(goods.getPictUrl()).into((ImageView) baseViewHolder.findView(R.id.img_second));
        }
    }
}
