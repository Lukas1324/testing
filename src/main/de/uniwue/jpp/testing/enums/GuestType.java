package de.uniwue.jpp.testing.enums;

public enum GuestType implements DiscountFactorProvider{
    STUDENT{
        @Override
        public double getDiscountFactor(){
            return 0.6;
        }
    },
    STAFF{
        @Override
        public double getDiscountFactor() {
            return 0.8;
        }
    },
    GUEST{
        @Override
        public double getDiscountFactor() {
            return 1.0;
        }
    };

    public double getDiscountFactor(){
        throw new UnsupportedOperationException();
    }
}
