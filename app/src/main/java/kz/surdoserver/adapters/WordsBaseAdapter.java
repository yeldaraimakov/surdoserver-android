package kz.surdoserver.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

import kz.surdoserver.R;
import kz.surdoserver.pojo.SurdoWord;

public class WordsBaseAdapter extends BaseAdapter {

    private LayoutInflater wordsInf;
    private final Context context;
    private final List<SurdoWord> wordList;

    public WordsBaseAdapter(Context context, List<SurdoWord> wordList) {
        this.context = context;
        this.wordList = wordList;
        this.wordsInf = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.wordList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {

            // get layout from xml file
            convertView = wordsInf.inflate(R.layout.word_list_item, null);

            holder = new ViewHolder();

            // pull views
            holder.name = (TextView) convertView.findViewById(R.id.word_name);
            holder.firstChar = (ImageView) convertView.findViewById(R.id.first_char);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String wordName = "ru".equals(getLang()) ? wordList.get(position).getNameRU() : wordList.get(position).getNameKZ();

        holder.name.setText(wordName);

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int randColor = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(wordName.charAt(0) + "", randColor); // radius in px

        holder.firstChar.setImageDrawable(drawable);

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        ImageView firstChar;
    }

    private String getLang() {
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        return conf.locale.getLanguage();
    }
}
