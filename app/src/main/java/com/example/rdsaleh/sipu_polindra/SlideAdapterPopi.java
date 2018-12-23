package com.example.rdsaleh.sipu_polindra;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideAdapterPopi extends PagerAdapter {

    Context ctx;
    LayoutInflater LI;

    //list of images
    public int[] list_images = {
            R.drawable.sejarah,
            R.drawable.misi2,
            R.drawable.popi_football,
            R.drawable.popi_badminton,
            R.drawable.popi_basketball
    };

    //list of title
    public String[] list_title = {
            "P O P I",
            "V I S I",
            "Bidang Olahraga",
            "Bidang Olahraga",
            "Bidang Olahraga"
    };

    //Llist of description
    public String[] list_desc = {
            "'' POPI adalah organisasi bersifat Unit Kegiatan Mahasiswa yang berfokus pada kegiatan dalam Bidang Olahraga ''",
            "'' Menyalurkan minat & bakat anggota dibidang olahraga sehingga dapat meningkatkan kualitas dan prestasi dibidang olahraga bagi almamater dengan menjungjung tinggi sportivitas mahasiswa Politeknik Negeri Indramayu ''",
            "'' Sepak Bola atau Futsal ''",
            "'' Badminton ''",
            "'' Basket ''"
    };

    //Llist of ig
//    public String[] list_ig = {
//            " ig | @popipolindra_official | ",
//            " ig | @popipolindra_official | ",
//            " ig | @popipolindra_official | ",
//            " ig | @popipolindra_official | ",
//            " ig | @popipolindra_official | "
//    };

    //list of backgrounds colors
    public int[] list_bg = {
            Color.rgb(76,166,76),
            Color.rgb(239,85,85),
            Color.rgb(202,163,255),
            Color.rgb(167,196,76),
            Color.rgb(107,229,250)
    };


    public SlideAdapterPopi(Context ctx){
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LI = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View v = LI.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = (LinearLayout) v.findViewById(R.id.slideLL);
        ImageView imgslide = (ImageView) v.findViewById(R.id.slideImg);
        TextView textTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView textDesc  = (TextView) v.findViewById(R.id.txtDescription);
        TextView textIg    = (TextView) v.findViewById(R.id.txtig);

        layoutslide.setBackgroundColor(list_bg[position]);
        imgslide.setImageResource(list_images[position]);
        textTitle.setText(list_title[position]);
        textDesc.setText(list_desc[position]);
//        textIg.setText(list_ig[position]);

        textIg.setVisibility(View.INVISIBLE);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(LinearLayout)o);
    }
}

