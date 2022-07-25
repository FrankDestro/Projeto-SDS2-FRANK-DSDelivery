import { useEffect, useState } from 'react';
import { fetchProducts } from '../api';
import OrdersLocation from './OrderLocation';
import ProductsList from './ProductsList';
import StepsHeader from './StepsHeader';
import './styles.css'
import { Product } from './types';

function Orders() {
    
    const [products, setProducts] = useState<Product[]>([]);
     console.log(products);

    useEffect(() => {
      fetchProducts()
      .then(response => setProducts(response.data) )
      .catch(error => console.log(error))                  
    },[]);

    return (
        <div className='orders-container'>
            <StepsHeader />
            <ProductsList products={products} />
            <OrdersLocation />
        </div>
    )
}

export default Orders;