package com.example.bd0631.goldseeker.database


import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PickUpLacationsRepo @Inject constructor(private val pickUpLocationDao: PickUpLocationDao) {

  fun getAllPickUpLocations(): Observable<List<PickUpLocation>> {
    return pickUpLocationDao.getPickUpLocations()
        .toObservable()
  }

  fun insertPickUpLocations(pickUpLocation: PickUpLocation) {
    pickUpLocationDao.insertNewPickUpItems(pickUpLocation)
  }

  fun removePickUpLocation(id: Long?){
    pickUpLocationDao.removePickUpLocation(id)
  }

  fun getSinglePickUpLocation(id: Long): Observable<PickUpLocation> {
    return pickUpLocationDao.getSinglePickUpLocation(id)
        .toObservable()
  }

}