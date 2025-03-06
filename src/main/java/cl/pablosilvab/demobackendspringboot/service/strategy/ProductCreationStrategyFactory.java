package cl.pablosilvab.demobackendspringboot.service.strategy;

import cl.pablosilvab.demobackendspringboot.entity.ProductType;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductCreationStrategyFactory {

    private final ElectronicProductCreationStrategy electronicProductCreationStrategy;
    private final OtherProductCreationStrategy otherProductCreationStrategy;

    public ProductCreationStrategyFactory(ElectronicProductCreationStrategy electronicProductCreationStrategy,
                                          OtherProductCreationStrategy otherProductCreationStrategy) {
        this.electronicProductCreationStrategy = electronicProductCreationStrategy;
        this.otherProductCreationStrategy = otherProductCreationStrategy;
    }

    public ProductCreationStrategy getStrategy(ProductType productType){
        if (Objects.requireNonNull(productType) == ProductType.ELECTRONIC) {
            return electronicProductCreationStrategy;
        }
        return otherProductCreationStrategy;
    }

}
