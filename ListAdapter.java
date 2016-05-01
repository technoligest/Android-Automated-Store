public class ListAdapter extends BaseAdapter {
    String [] value;
    int [] thumb;
    Context context;

    private static LayoutInflater inflater=null;
    public ListAdapter(MainActivity mainActivity, String[] title, int[] images) {
        // TODO Auto-generated constructor stub
        value=title;
        thumb=images;
        context=mainActivity;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return value.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView titleView;
        ImageView imgView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_item, null);
        holder.titleView=(TextView) rowView.findViewById(R.id.titleTextView);
        holder.imgView=(ImageView) rowView.findViewById(R.id.thumbnailView);

        holder.titleView.setText(value[position]);
        holder.imgView.setImageResource(thumb[position]);
        holder.imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int itemPosition = position;
                Toast.makeText(v.getContext(), "Position :" + itemPosition + "  ListItem : " +
                        value[position], Toast.LENGTH_LONG).show();

                Intent i = new Intent(v.getContext(), bananas.class);
                v.getContext().startActivity(i);

            }
        });
        return rowView;
    }

}
