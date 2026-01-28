import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getPropertyById, createBooking } from '../services/api';
import '../styles/PropertyDetails.css';

function PropertyDetails() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [property, setProperty] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [bookingData, setBookingData] = useState({
    checkInDate: '',
    checkOutDate: '',
    numberOfGuests: 1,
  });

  useEffect(() => {
    fetchProperty();
  }, [id]);

  const fetchProperty = async () => {
    try {
      const response = await getPropertyById(id);
      setProperty(response.data);
      setLoading(false);
    } catch (err) {
      setError('Failed to load property details');
      setLoading(false);
    }
  };

  const calculateTotalPrice = () => {
    if (!bookingData.checkInDate || !bookingData.checkOutDate || !property) {
      return 0;
    }
    const checkIn = new Date(bookingData.checkInDate);
    const checkOut = new Date(bookingData.checkOutDate);
    const nights = Math.ceil((checkOut - checkIn) / (1000 * 60 * 60 * 24));
    return nights > 0 ? nights * property.pricePerNight : 0;
  };

  const handleBooking = async (e) => {
    e.preventDefault();
    
    const token = localStorage.getItem('token');
    if (!token) {
      navigate('/login');
      return;
    }

    try {
      const totalPrice = calculateTotalPrice();
      await createBooking({
        propertyId: property.id,
        checkInDate: bookingData.checkInDate,
        checkOutDate: bookingData.checkOutDate,
        numberOfGuests: bookingData.numberOfGuests,
        totalPrice: totalPrice,
      });
      alert('Booking successful!');
      navigate('/bookings');
    } catch (err) {
      alert('Booking failed. Please try again.');
    }
  };

  if (loading) return <div className="loading">Loading...</div>;
  if (error) return <div className="error">{error}</div>;
  if (!property) return <div className="error">Property not found</div>;

  return (
    <div className="property-details-container">
      <div className="property-header">
        <h1>{property.title}</h1>
        <p className="property-location">{property.address}, {property.city}, {property.country}</p>
      </div>

      <div className="property-content">
        <div className="property-image-section">
          {property.imageUrl ? (
            <img src={property.imageUrl} alt={property.title} className="main-image" />
          ) : (
            <div className="placeholder-image-large">No Image Available</div>
          )}
        </div>

        <div className="property-info">
          <div className="info-section">
            <h2>About this property</h2>
            <p>{property.description || 'No description available'}</p>
          </div>

          <div className="info-section">
            <h3>Property Details</h3>
            <ul className="details-list">
              <li>Type: {property.propertyType}</li>
              <li>Max Guests: {property.maxGuests}</li>
              <li>Bedrooms: {property.bedrooms}</li>
              <li>Bathrooms: {property.bathrooms}</li>
            </ul>
          </div>

          <div className="booking-section">
            <h3>Book this property</h3>
            <p className="price">${property.pricePerNight} / night</p>
            
            <form onSubmit={handleBooking}>
              <div className="form-group">
                <label>Check-in Date</label>
                <input
                  type="date"
                  value={bookingData.checkInDate}
                  onChange={(e) => setBookingData({...bookingData, checkInDate: e.target.value})}
                  required
                  min={new Date().toISOString().split('T')[0]}
                />
              </div>

              <div className="form-group">
                <label>Check-out Date</label>
                <input
                  type="date"
                  value={bookingData.checkOutDate}
                  onChange={(e) => setBookingData({...bookingData, checkOutDate: e.target.value})}
                  required
                  min={bookingData.checkInDate || new Date().toISOString().split('T')[0]}
                />
              </div>

              <div className="form-group">
                <label>Number of Guests</label>
                <input
                  type="number"
                  value={bookingData.numberOfGuests}
                  onChange={(e) => setBookingData({...bookingData, numberOfGuests: parseInt(e.target.value)})}
                  min="1"
                  max={property.maxGuests}
                  required
                />
              </div>

              {calculateTotalPrice() > 0 && (
                <div className="total-price">
                  <strong>Total: ${calculateTotalPrice()}</strong>
                </div>
              )}

              <button type="submit" className="btn-primary btn-book">Book Now</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PropertyDetails;
