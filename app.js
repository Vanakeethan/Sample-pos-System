import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ProductList from './components/ProductList';
import OrderForm from './components/OrderForm';

function App() {
  return (
    <Router>
      <div>
        <h1>Point of Sale System</h1>
        <Switch>
          <Route path="/products" component={ProductList} />
          <Route path="/orders" component={OrderForm} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
