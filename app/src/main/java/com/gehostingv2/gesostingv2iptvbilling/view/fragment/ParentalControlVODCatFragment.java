package com.gehostingv2.gesostingv2iptvbilling.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.gehostingv2.gesostingv2iptvbilling.R;
import com.gehostingv2.gesostingv2iptvbilling.model.database.LiveStreamCategoryIdDBModel;
import com.gehostingv2.gesostingv2iptvbilling.model.database.LiveStreamDBHandler;
import com.gehostingv2.gesostingv2iptvbilling.model.pojo.InvoicesDetailPojo;
import com.gehostingv2.gesostingv2iptvbilling.presenter.InvoicesPresenter;
import com.gehostingv2.gesostingv2iptvbilling.view.activity.ParentalControlActivity;
import com.gehostingv2.gesostingv2iptvbilling.view.adapter.ParentalControlLiveCatgoriesAdapter;
import com.gehostingv2.gesostingv2iptvbilling.view.adapter.ParentalControlVODCatAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.fabric.sdk.android.Fabric;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ParentalControlVODCatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ParentalControlVODCatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParentalControlVODCatFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @BindView(R.id.empty_view)
    TextView emptyView;



    @BindView(R.id.pb_loader)
    ProgressBar pbLoader;
    private ArrayList<InvoicesDetailPojo> arraylist;
    private InvoicesPresenter invoicesPresenter;
    private FragmentActivity myContext;
    private ParentalControlVODCatAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog progressDialog;
    private Typeface fontOPenSansBold;
    private Toolbar toolbar;
    private SearchView searchView;


    Context context;
    Unbinder unbinder;

    private OnFragmentInteractionListener mListener;

    public ParentalControlVODCatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParentalControlVODCatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParentalControlVODCatFragment newInstance(String param1, String param2) {
        ParentalControlVODCatFragment fragment = new ParentalControlVODCatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_parental_control_vodcat, container, false);

        Fabric.with(getContext(), new Crashlytics());
        setHasOptionsMenu(false);
        View view = inflater.inflate(R.layout.fragment_parental_control_vodcat, container, false);
        unbinder = ButterKnife.bind(this, view);
//        initialize();
        initializeData();
        setMenuBar();
        return view;
    }

    private void initializeData() {
        context = getContext();

        fontOPenSansBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/open_sans.ttf");

        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        myRecyclerView.setLayoutManager(mLayoutManager);

        LiveStreamDBHandler liveStreamDBHandler = new LiveStreamDBHandler(context);
//        ArrayList<LiveStreamCategoryIdDBModel> liveCategories = liveStreamDBHandler.getAllMovieCategories();
        ArrayList<LiveStreamCategoryIdDBModel> liveCategories = liveStreamDBHandler.getAllMovieCategoriesHavingParentIdZero();
        // create map to store
        Map<String, String> map = new HashMap<String, String>();
        for(LiveStreamCategoryIdDBModel listItem : liveCategories){
            map.put(listItem.getLiveStreamCategoryID(),listItem.getLiveStreamCategoryName());
        }
        String[] categoriesArray = map.values().toArray(new String[0]);


        if(pbLoader!=null)
            pbLoader.setVisibility(View.INVISIBLE);
        if (liveCategories.size() > 0 && myRecyclerView != null && emptyView != null) {
            myRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            mAdapter = new ParentalControlVODCatAdapter(liveCategories, getContext(), fontOPenSansBold);
            myRecyclerView.setAdapter(mAdapter);

        } else if (liveCategories.isEmpty()) {
            if (myRecyclerView != null && emptyView != null) {
                myRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                emptyView.setText("No Categories Found");
            }
        }
    }
    private void setMenuBar() {
        setHasOptionsMenu(true);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
