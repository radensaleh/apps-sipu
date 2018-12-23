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

public class SlideAdapterKopen extends PagerAdapter {

    Context ctx;
    LayoutInflater LI;

    //list of images
    public int[] list_images = {
            R.drawable.sejarah,
            R.drawable.misi2,
            R.drawable.misi2,
            R.drawable.kopen_editor_image,
            R.drawable.kopen,
            R.drawable.kopen_reporter,
            R.drawable.kopen_penulis,
            R.drawable.kopen_videography,
            R.drawable.kopen_photography
    };

    //list of title
    public String[] list_title = {
            "S E J A R A H",
            "V I S I",
            "M O T T O",
            "DIVISI DESAIN MEDIA",
            "DIVISI DESAIN MEDIA",
            "DIVISI PERS",
            "DIVISI PERS",
            "DIVISI BROADCASTING",
            "DIVISI BROADCASTING"
    };

    //Llist of description
    public String[] list_desc = {
            "'' Kotak Pena Polindra didirikan pada tanggal 21 April 2013  ''",
            "'' Menumbuhkan kreativitas dan semangat berkarya mahasiswa " + "\n" + " Politeknik Negeri Indramayu dengan mengimplementasikannya dalam media padat karya dan penyampaian informasi ''",
            "'' Satu Hati, Satu Persepsi, Satu Visi, Satu Misi, Satu Dedikasi, Satu Kontribusi, Banyak Prestasi ''",
            "'' Editor Image ''",
            "'' Editor Video ''",
            "'' Reporter ''",
            "'' Penulis ''",
            "'' Videography ''",
            "'' Photography ''"
    };

    //Llist of ig
//    public String[] list_ig = {
//            " ig | @kotakpena | ",
//            " ig | @kotakpena | ",
//            " ig | @kotakpena | ",
//            " ig | @kotakpena | ",
//            " ig | @kotakpena | "
//    };

    //list of backgrounds colors
    public int[] list_bg = {
            Color.rgb(76,166,76),
            Color.rgb(239,85,85),
            Color.rgb(167,196,76),
            Color.rgb(255,183,50),
            Color.rgb(202,163,255),
            Color.rgb(107,229,250),
            Color.rgb(231,98,54),
            Color.rgb(123,193,89),
            Color.rgb(239,85,85)
    };


    public SlideAdapterKopen(Context ctx){
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
