package my.all.notes.OnBoarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import my.all.notes.R;


public class IntroViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ScreenItem> screenItemList;

    public IntroViewPagerAdapter(Context context, List<ScreenItem> screenItemList) {
        this.context = context;
        this.screenItemList = screenItemList;
    }

    @Override
    public int getCount() {
        return screenItemList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.layout_screen, null);
        TextView title = v.findViewById(R.id.intro_title);
        TextView descr = v.findViewById(R.id.intro_desription);
        ImageView img = v.findViewById(R.id.intro_img);

        title.setText(screenItemList.get(position).getTitle());
        descr.setText(screenItemList.get(position).getDescription());
        img.setImageResource(screenItemList.get(position).getImageImg());

        container.addView(v);

        return v;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
