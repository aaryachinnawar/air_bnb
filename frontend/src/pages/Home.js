import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getAllProperties } from '../services/api';
import '../styles/Home.css';

function Home() {
  const [properties, setProperties] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchProperties();
  }, []);

  const fetchProperties = async () => {
    try {
      const response = await getAllProperties();
      setProperties(response.data);
      setLoading(false);
    } catch (err) {
      setError('Failed to load properties');
      setLoading(false);
    }
  };

  if (loading) return <div className="loading">Loading...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="home-container">
      <section className="hero">
        <h1>Find Your Perfect Stay</h1>
        <p>Book unique homes and experiences around the world</p>
      </section>

      <section className="properties-grid">
        <h2>Explore Properties</h2>
        <div className="grid">
          {properties.length === 0 ? (
            <p className="no-properties">No properties available at the moment.</p>
          ) : (
            properties.map((property) => (
              <Link to={`/properties/${property.id}`} key={property.id} className="property-card">
                <div className="property-image">
                  {property.imageUrl ? (
                    <img src={property.imageUrl} alt={property.title} />
                  ) : (
                    <div className="placeholder-image">No Image</div>
                  )}
                </div>
                <div className="property-details">
                  <h3>{property.title}</h3>
                  <p className="property-location">{property.city}, {property.country}</p>
                  <p className="property-type">{property.propertyType}</p>
                  <div className="property-footer">
                    <span className="property-price">${property.pricePerNight} / night</span>
                  </div>
                </div>
              </Link>
            ))
          )}
        </div>
      </section>
    </div>
  );
}

export default Home;
