package com.saywa.framework.data.s3.domain;

/**
 * Type of Amazon S3 operation performed by {@code S3StorageService}, used
 * mainly to classify audit events.
 */
public enum S3Operation {

    /** Upload of an object to the bucket. */
    PUT,

    /** Download of an object's content from the bucket. */
    GET,

    /** Deletion of an object from the bucket. */
    DELETE,

    /** Listing of objects in the bucket, optionally filtered by prefix. */
    LIST,

    /** Copy of an object to another key, within the same bucket. */
    COPY,

    /** Lookup of an object's metadata without downloading its content. */
    HEAD,

    /** Generation of a presigned URL for temporary access to an object. */
    PRESIGN
}
