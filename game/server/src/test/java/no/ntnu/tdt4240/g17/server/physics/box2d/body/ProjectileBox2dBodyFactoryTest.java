package no.ntnu.tdt4240.g17.server.physics.box2d.body;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Kristian 'krissrex' Rekstad on 3/12/2019.
 *
 * @author Kristian 'krissrex' Rekstad
 */
class ProjectileBox2dBodyFactoryTest {

    private World world;
    private Entity entity;

    @BeforeAll
    static void setUpBox2d() {
        Box2D.init();
    }

    @BeforeEach
    void setUp() {
        world = new World(new Vector2(0, -9.81f), true);
        entity = new Entity();
    }

    @Test
    void shouldCreateBody() {
        // Given
        final ProjectileBox2dBodyFactory factory = new ProjectileBox2dBodyFactory(world);

        // When
        final Body body = factory.create(entity);

        // Then
        assertNotNull(body);
        assertThat(body.getFixtureList().size, is(1));
        assertFalse(body.isFixedRotation());
        assertThat(body.getMass(), is(greaterThan(0f)));
    }

    @Test
    void shouldHaveLeftSideAtOrigo() {
        // Given
        final ProjectileBox2dBodyFactory factory = new ProjectileBox2dBodyFactory(world);

        // When
        final Body body = factory.create(entity);
        final Fixture fixture = body.getFixtureList().get(0);
        final Vector2 tailCoordinate = factory.getTailCoordinate();

        // Then
        assertTrue(fixture.testPoint(0, 0), "Is not at origo");
        assertTrue(fixture.testPoint(tailCoordinate.x, tailCoordinate.y));
        assertThat("Tail is not at origo", tailCoordinate.x, is(greaterThanOrEqualTo(0f)));
        assertThat("Tail is not at origo", tailCoordinate.y, is(greaterThanOrEqualTo(0f)));
        assertFalse(fixture.testPoint(-0.1f, 0), "Arrow exists left of origo");
    }
}