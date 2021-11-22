package com.bawei.home.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bawei.home.BR;
import com.bawei.home.R;
import com.bawei.home.databinding.HomeFragmentBinding;
import com.bawei.home.entity.GoodsEntity;
import com.bawei.home.viewmodel.HomeCenterViewModel;
import com.bawei.mvvmcore.view.BaseMVVMFragment;
import com.bawei.net.procotol.BaseRespEntity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class HomeFragment extends BaseMVVMFragment<HomeCenterViewModel, HomeFragmentBinding> {

    private View bandView;
    private RecyclerView homeRv;
    ArrayList<GoodsEntity> goodsEntities;
    private Banner banner;

    @Override
    protected Integer getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected HomeCenterViewModel createViewModel() {
        return new HomeCenterViewModel(this);
    }

    @Override
    protected void prepareSetVars(HashMap mMap) {

        mMap.put(BR.type, mViewModel.source);
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    protected void initEnv() {
        mViewModel.source.setValue(new ArrayList<>());
    }

    @Override
    protected void initView(View view) {
        homeRv = view.findViewById(R.id.home_rv);
        homeRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        banner = view.findViewById(R.id.banner);

    }


    @Override
    protected void loadData() {

        Random random = new Random();

        int i = random.nextInt(10);

        mViewModel.getType();

        ArrayList<String> imgs = new ArrayList<>();

        mViewModel.getGoods(i).observe(this, new Observer<BaseRespEntity<List<GoodsEntity>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<GoodsEntity>> listBaseRespEntity) {

                if (listBaseRespEntity.getData() != null && listBaseRespEntity.getCode() == 200) {

                    goodsEntities = new ArrayList<>();

                    for (int i = 0; i < listBaseRespEntity.getData().size(); i++) {

                        GoodsEntity goodsEntity = listBaseRespEntity.getData().get(i);

                        String pictUrl = listBaseRespEntity.getData().get(i).getPictUrl();

                        imgs.add(pictUrl);

                        goodsEntities.add(goodsEntity);
                    }

                    MyAdapter myAdapter = new MyAdapter(goodsEntities);

                    homeRv.setAdapter(myAdapter);

                    banner.setImages(imgs);

                    banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(getActivity()).load(path).into(imageView);
                        }
                    });
                    banner.start();


                } else {

                    Toast.makeText(mActivity, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return bandView = inflater.inflate(R.layout.home_fragment, container, false);
    }


    public class MyAdapter extends BaseQuickAdapter<GoodsEntity, BaseViewHolder> {

        public MyAdapter(@Nullable List<GoodsEntity> data) {
            super(R.layout.item_xsms, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, GoodsEntity goodsEntity) {

            baseViewHolder.setText(R.id.title, goodsEntity.getCategoryName());
            baseViewHolder.setText(R.id.title2, goodsEntity.getShortTitle());
            Glide.with(getContext()).load(goodsEntity.getPictUrl()).into((ImageView) baseViewHolder.getView(R.id.pic));

        }
    }


}
