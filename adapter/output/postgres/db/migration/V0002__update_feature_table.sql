ALTER TABLE "feature_manager"."feature"
ADD CONSTRAINT unq_external_id UNIQUE
(external_id);