package com.mchavez.pettabbed.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.mchavez.pettabbed.adapter.Mascota;
import com.mchavez.pettabbed.adapter.PetAdapter;
import com.mchavez.pettabbed.R;
import com.mchavez.pettabbed.presentador.IRvFragmentPresenter;
import com.mchavez.pettabbed.presentador.RvFragmentPresenter;


public class Fragment_RV extends Fragment implements IFragment_Rv {

    private ArrayList<Mascota> pets;
    private RecyclerView rv_fragment;
    private PetAdapter adapter;
    private IRvFragmentPresenter presenter;
    private Activity activity;

    /*public Fragment_RV() {
        // Required empty public constructor
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rv, container, false);
        rv_fragment = v.findViewById(R.id.rvPets);
        presenter = new RvFragmentPresenter(this, getContext());

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_fragment.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getContext(),2);
        rv_fragment.setLayoutManager(glm);

    }

    @Override
    public PetAdapter crearAdaptador(ArrayList<Mascota> pets) {
        PetAdapter adapter = new PetAdapter(pets);
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(PetAdapter adapter) {
        rv_fragment.setAdapter(adapter);

    }
}

