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

import org.w3c.dom.Text;

public class SlideAdapter extends PagerAdapter {

    Context ctx;
    LayoutInflater LI;

    //list of images
    public int[] list_images = {
            R.drawable.kompa,
            R.drawable.kopen,
            R.drawable.rpi,
            R.drawable.popi,
            R.drawable.folafo
    };

    //list of title
    public String[] list_title = {
            "K O M P A",
            "K O T A K - P E N A",
            "R P I",
            "P O P I",
            "F O L A F O"
    };

    //Llist of description
    public String[] list_desc = {
            "'' Komunitas Mahasiswa Pecinta Alam \n Politeknik Negeri Indramayu ''",
            "'' Komunitas Mahasiswa \n Jurnalistik Pencinta Karya \n Politeknik Negeri Indramayu ''",
            "'' R o b o t i k a \n Politeknik Negeri Indramayu ''",
            "'' Pekan Olahraga \n Politeknik Negeri Indramayu ''",
            "'' Foreign Languages Forum of \n State Polytechnic of Indramayu ''"
    };

    //Llist of ig
    public String[] list_ig = {
            " ig | @kompa.polindra | ",
            " ig | @kotakpena | ",
            " ig | @robotika_polindra | ",
            " ig | @popipolindra_official | ",
            " ig | @folafo_polindra | "
    };

    //list of backgrounds colors
    public int[] list_bg = {
            Color.rgb(55,55,35),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(22,180,21),
            Color.rgb(1,188,212)
    };


    public SlideAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(LinearLayout)o);
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
        textIg.setText(list_ig[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

}
