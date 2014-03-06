package org.cdispec.event;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 * @author Antoine Sabot-Durand
 */

@RunWith(Arquillian.class)
public class EventTest {

    @Deployment
    public static Archive<?> createTestArchive() throws FileNotFoundException {

        WebArchive ret = ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addClasses(ObservingBean.class,
                        Payload.class,
                        Qualified.class,
                        QualifiedLiteral.class,
                        QualifiedAgain.class,
                        QualifiedAgainLiteral.class
                )
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        return ret;
    }


    @Inject
    //@Any
            Event<Payload> payLoadEvent;

    @Test
    public void shouldResolveUnqualifiedObserver() {
        Payload myPl = new Payload();
        payLoadEvent.fire(myPl);

        Assert.assertEquals(10, myPl.content);
    }


    @Test
    public void shouldResolvedQualifiedAndUnqualifiedObserver() {
        Payload myPl = new Payload();
        payLoadEvent.select(new QualifiedLiteral()).fire(myPl);

        Assert.assertEquals(110, myPl.content);
    }

    @Test
    public void shouldResolvedQualifiedWithParamAndUnqualifiedObserver() {
        Payload myPl = new Payload();
        payLoadEvent.select(new QualifiedLiteral("special")).fire(myPl);

        Assert.assertEquals(120, myPl.content);
    }


    @Test
    public void shouldResolvedQualifiedAgainAndUnqualifiedObserver() {
        Payload myPl = new Payload();
        payLoadEvent.select(new QualifiedAgainLiteral()).fire(myPl);

        Assert.assertEquals(1010, myPl.content);
    }


    @Test
    public void shouldResolvedQualifiedQualifiedAgainAndUnqualifiedObserver() {
        Payload myPl = new Payload();
        payLoadEvent.select(new QualifiedAgainLiteral(), new QualifiedLiteral()).fire(myPl);

        Assert.assertEquals(11110, myPl.content);
    }
}
