//package com.tasfia.orphanhouse;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.github.chrisbanes.photoview.PhotoView;
//
//import androidx.annotation.NonNull;
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//public class ViewPagerAdapter extends PagerAdapter {
//
//
//    private Context mContext;
//    private LayoutInflater layoutInflater;
//    private Integer[] images = {
//            R.drawable.gallery1, R.drawable.gallery2, R.drawable.gallery3,
//            R.drawable.gallery4, R.drawable.gallery5, R.drawable.gallery6,
//            R.drawable.gallery7, R.drawable.gallery8, R.drawable.gallery9,
//            R.drawable.gallery10, R.drawable.gallery11, R.drawable.gallery12,
//            R.drawable.gallery13, R.drawable.gallery14, R.drawable.gallery15,
//            R.drawable.gallery16, R.drawable.gallery17, R.drawable.gallery18,
//            R.drawable.gallery19, R.drawable.gallery20, R.drawable.gallery21,
//            R.drawable.gallery22, R.drawable.gallery23, R.drawable.gallery24,
//            R.drawable.gallery25, R.drawable.gallery26, R.drawable.gallery27,
//            R.drawable.gallery28, R.drawable.gallery29, R.drawable.gallery30
//    };
//
//    public ViewPagerAdapter(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    @Override
//    public int getCount() {
//        return images.length;
//    }
//
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//        return view == o;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.custom_layout, null);
//
//        PhotoView photoView = (PhotoView) view.findViewById(R.id.myPhotoView);
//        photoView.setImageResource(images[position]);
//
//        ViewPager viewPager = (ViewPager) container;
//        viewPager.addView(view);
//        return view;
//    }
//
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        ViewPager viewPager = (ViewPager) container;
//        View view = (View) object;
//        viewPager.removeView(view);
//    }
//}