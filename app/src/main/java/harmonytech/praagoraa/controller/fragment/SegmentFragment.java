package harmonytech.praagoraa.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import harmonytech.praagoraa.R;
import harmonytech.praagoraa.controller.adapter.SegmentAdapter;
import harmonytech.praagoraa.controller.domain.Segment;
import harmonytech.praagoraa.controller.interfaces.RecyclerViewOnClickListenerHack;
import harmonytech.praagoraa.controller.util.Utility;
import harmonytech.praagoraa.view.MainActivity;
import harmonytech.praagoraa.view.RegisterServiceActivity;
import harmonytech.praagoraa.view.SegmentCategoryActivity;

public class SegmentFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    RecyclerView mRecyclerView;
    public List<Segment> mList;
    public SegmentAdapter adapter;
    Spinner spinnerCity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_segment, container, false);

        spinnerCity = (Spinner) getActivity().findViewById(R.id.main_sp_city);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mList = ((MainActivity) getActivity()).getRecipesList();
        adapter = new SegmentAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter( adapter );

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener( getActivity(), mRecyclerView, this ));

        return view;

    }

    @Override
    public void onClickListener(View view, int position) {
        if(!spinnerCity.getSelectedItem().equals("Cidade")) {
            Intent intent = new Intent(getActivity(), SegmentCategoryActivity.class);
            intent.putExtra(Utility.SEGMENTO, mList.get(position).getName());
            intent.putExtra(Utility.SEGMENTO_FIREBASE, mList.get(position).getNameFirebase());
            intent.putExtra(Utility.KEY_CONTENT_EXTRA_CITY, spinnerCity.getSelectedItem().toString());
            startActivity(intent);
        }else{
            Toast.makeText(getActivity(),
                    "Por favor, selecione a cidade antes de clicar em alguma categoria",
                    Toast.LENGTH_LONG).show();
        }
    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh){
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if(cv != null && mRecyclerViewOnClickListenerHack != null){
                        mRecyclerViewOnClickListenerHack.onClickListener(cv,
                                rv.getChildAdapterPosition(cv) );
                    }
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
}
