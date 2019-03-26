package njit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import njit.dao.BaseDao;
import njit.dao.BookingDao;
import njit.model.Booking;

@Service("bookingService")
public class BookingServiceImp extends BaseServiceImp<Booking> implements BookingService{

	@Autowired
	private BookingDao bookingDao;
	@Override
	public BaseDao getBaseDao() {
		return bookingDao;
	}

}
