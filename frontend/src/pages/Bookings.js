import React, { useState, useEffect } from 'react';
import { getUserBookings, cancelBooking } from '../services/api';
import { useNavigate } from 'react-router-dom';
import '../styles/Bookings.css';

function Bookings() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      navigate('/login');
      return;
    }
    fetchBookings();
  }, [navigate]);

  const fetchBookings = async () => {
    try {
      const response = await getUserBookings();
      setBookings(response.data);
      setLoading(false);
    } catch (err) {
      setError('Failed to load bookings');
      setLoading(false);
    }
  };

  const handleCancel = async (bookingId) => {
    if (window.confirm('Are you sure you want to cancel this booking?')) {
      try {
        await cancelBooking(bookingId);
        fetchBookings();
      } catch (err) {
        alert('Failed to cancel booking');
      }
    }
  };

  if (loading) return <div className="loading">Loading...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="bookings-container">
      <h1>My Bookings</h1>
      
      {bookings.length === 0 ? (
        <div className="no-bookings">
          <p>You don't have any bookings yet.</p>
          <button onClick={() => navigate('/')} className="btn-primary">
            Explore Properties
          </button>
        </div>
      ) : (
        <div className="bookings-list">
          {bookings.map((booking) => (
            <div key={booking.id} className="booking-card">
              <div className="booking-info">
                <h3>{booking.property.title}</h3>
                <p className="booking-location">
                  {booking.property.city}, {booking.property.country}
                </p>
                <div className="booking-dates">
                  <p><strong>Check-in:</strong> {new Date(booking.checkInDate).toLocaleDateString()}</p>
                  <p><strong>Check-out:</strong> {new Date(booking.checkOutDate).toLocaleDateString()}</p>
                  <p><strong>Guests:</strong> {booking.numberOfGuests}</p>
                </div>
                <p className="booking-total">
                  <strong>Total:</strong> ${booking.totalPrice}
                </p>
                <p className={`booking-status status-${booking.status.toLowerCase()}`}>
                  Status: {booking.status}
                </p>
              </div>
              {booking.status === 'CONFIRMED' && (
                <button 
                  onClick={() => handleCancel(booking.id)} 
                  className="btn-cancel"
                >
                  Cancel Booking
                </button>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Bookings;
