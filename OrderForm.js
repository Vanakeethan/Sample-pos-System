import React, { useState } from 'react';

function OrderForm() {
  const [orderItems, setOrderItems] = useState([]);
  const [productToAdd, setProductToAdd] = useState('');
  const [quantityToAdd, setQuantityToAdd] = useState(1);

  const addProductToOrder = () => {
    // Check if the product is already in the order
    const existingItemIndex = orderItems.findIndex(item => item.product === productToAdd);
    if (existingItemIndex !== -1) {
      // Update quantity if the product is already in the order
      const updatedOrderItems = [...orderItems];
      updatedOrderItems[existingItemIndex].quantity += quantityToAdd;
      setOrderItems(updatedOrderItems);
    } else {
      // Add the product to the order
      setOrderItems([...orderItems, { product: productToAdd, quantity: quantityToAdd }]);
    }
    // Clear input fields after adding the product to the order
    setProductToAdd('');
    setQuantityToAdd(1);
  };

  const removeProductFromOrder = (indexToRemove) => {
    const updatedOrderItems = orderItems.filter((item, index) => index !== indexToRemove);
    setOrderItems(updatedOrderItems);
  };

  const submitOrder = () => {
    // Implement functionality to submit order
    console.log('Order submitted:', orderItems);
    // Reset orderItems state after submitting the order
    setOrderItems([]);
  };

  return (
    <div>
      <h2>Order Form</h2>
      <div>
        <label>Product:</label>
        <input
          type="text"
          value={productToAdd}
          onChange={(e) => setProductToAdd(e.target.value)}
        />
        <label>Quantity:</label>
        <input
          type="number"
          value={quantityToAdd}
          onChange={(e) => setQuantityToAdd(parseInt(e.target.value))}
        />
        <button onClick={addProductToOrder}>Add Product</button>
      </div>
      <h3>Order Summary</h3>
      <ul>
        {orderItems.map((item, index) => (
          <li key={index}>
            {item.product} - Quantity: {item.quantity}
            <button onClick={() => removeProductFromOrder(index)}>Remove</button>
          </li>
        ))}
      </ul>
      <button onClick={submitOrder}>Submit Order</button>
    </div>
  );
}

export default OrderForm;
