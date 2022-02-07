package com.glanner.core.domain.glanner;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.processing.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QDailyWorkGlanner is a Querydsl query type for DailyWorkGlanner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDailyWorkGlanner extends EntityPathBase<DailyWorkGlanner> {

    private static final long serialVersionUID = 1191497841L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDailyWorkGlanner dailyWorkGlanner = new QDailyWorkGlanner("dailyWorkGlanner");

    public final com.glanner.core.domain.base.QBaseTimeEntity _super = new com.glanner.core.domain.base.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final QGlanner glanner;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DateTimePath<java.time.LocalDateTime> notiDate = createDateTime("notiDate", java.time.LocalDateTime.class);

    public final EnumPath<com.glanner.core.domain.user.NotificationStatus> notiStatus = createEnum("notiStatus", com.glanner.core.domain.user.NotificationStatus.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public QDailyWorkGlanner(String variable) {
        this(DailyWorkGlanner.class, forVariable(variable), INITS);
    }

    public QDailyWorkGlanner(Path<? extends DailyWorkGlanner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDailyWorkGlanner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDailyWorkGlanner(PathMetadata metadata, PathInits inits) {
        this(DailyWorkGlanner.class, metadata, inits);
    }

    public QDailyWorkGlanner(Class<? extends DailyWorkGlanner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.glanner = inits.isInitialized("glanner") ? new QGlanner(forProperty("glanner"), inits.get("glanner")) : null;
    }

}

