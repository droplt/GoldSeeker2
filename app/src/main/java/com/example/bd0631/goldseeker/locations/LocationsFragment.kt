package com.example.bd0631.goldseeker.locations

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation
import com.example.bd0631.goldseeker.databinding.FragmentLocationsBinding
import com.example.bd0631.goldseeker.utils.LocationHelper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationsFragment : Fragment(), OnMapReadyCallback {

  lateinit var databinding: FragmentLocationsBinding
  lateinit var viewModel: LocationsViewModel

  private lateinit var map: GoogleMap
  val isVisible = MutableLiveData<Boolean>()
  var selected = false

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_locations, container, false)

    val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)
    return databinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = (activity as LocationsActivity).obtainViewModel()
    databinding.viewModel = viewModel
  }

  companion object {

    fun newInstance() = LocationsFragment()
  }

  override fun onMapReady(googleMap: GoogleMap) {

    viewModel.getThrowAwayItem()?.observe(this, Observer<List<PickUpLocation>> {
      setMarkers(it, googleMap)
    })

  }

  private fun setMarkers(it: List<PickUpLocation>?, googleMap: GoogleMap) {
    map = googleMap

    for(item in it!!.listIterator()) {
      if(item.latitude != null && item.longitude != null){
        map.addMarker(MarkerOptions().position(LatLng(item.longitude!!, item.latitude!!)).title(item.Address))
      }
    }
    map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(51.1, 10.4)))
    map.setOnMarkerClickListener {
      Toast.makeText(context, it.snippet, Toast.LENGTH_LONG).show()
      isVisible.value = true
      false
    }
  }
}