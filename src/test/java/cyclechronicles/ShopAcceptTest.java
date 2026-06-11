package cyclechronicles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

    public class ShopAcceptTest {

        @Test
        void acceptsNormalBikeWhenQueueIsEmpty() {
            Shop shop = new Shop();
            Order order = order(Type.RACE, "Kareem");

            assertTrue(shop.accept(order));
        }

        @Test
        void acceptsNormalBikeWhenFourOrdersArePending() {
            Shop shop = new Shop();

            shop.accept(order(Type.RACE, "Customer 1"));
            shop.accept(order(Type.RACE, "Customer 2"));
            shop.accept(order(Type.RACE, "Customer 3"));
            shop.accept(order(Type.RACE, "Customer 4"));

            assertTrue(shop.accept(order(Type.RACE, "Customer 5")));
        }

        @Test
        void rejectsNormalBikeWhenFiveOrdersArePending() {
            Shop shop = new Shop();

            shop.accept(order(Type.RACE, "Customer 1"));
            shop.accept(order(Type.RACE, "Customer 2"));
            shop.accept(order(Type.RACE, "Customer 3"));
            shop.accept(order(Type.RACE, "Customer 4"));
            shop.accept(order(Type.RACE, "Customer 5"));

            assertFalse(shop.accept(order(Type.RACE, "Customer 6")));
        }

        @Test
        void rejectsEBike() {
            Shop shop = new Shop();
            Order order = order(Type.EBIKE, "Kareem");

            assertFalse(shop.accept(order));
        }

        @Test
        void rejectsGravelBike() {
            Shop shop = new Shop();
            Order order = order(Type.GRAVEL, "Kareem");

            assertFalse(shop.accept(order));
        }

        @Test
        void rejectsOrderWhenCustomerAlreadyHasPendingOrder() {
            Shop shop = new Shop();

            shop.accept(order(Type.RACE, "Kareem"));

            assertFalse(shop.accept(order(Type.RACE, "Kareem")));
        }

        private Order order(Type type, String customer) {
            Order order = mock(Order.class);
            when(order.getBicycleType()).thenReturn(type);
            when(order.getCustomer()).thenReturn(customer);
            return order;
        }
    }
