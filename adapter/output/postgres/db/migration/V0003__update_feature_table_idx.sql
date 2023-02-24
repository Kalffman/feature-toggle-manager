ALTER TABLE "feature_manager"."feature"
ADD CONSTRAINT unq_external_id UNIQUE (name);

CREATE INDEX idx_feature_name ON feature_manager.feature (name);
